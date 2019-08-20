package org.gooru.ds.user.processor.content.portfolio.asmt.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioAsmtSummaryService {

  private final UserPortfolioItemSummaryDao userPortfolioItemSummaryDao;
  private final CoreContentsService coreContentsService;
  private UserPortfolioItemSummaryCommand command;
  private String contentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioAsmtSummaryService.class);

  UserPortfolioAsmtSummaryService(DBI dbi, DBI coreDbi) {
    this.userPortfolioItemSummaryDao = dbi.onDemand(UserPortfolioItemSummaryDao.class);
    this.coreContentsService = new CoreContentsService(coreDbi);
  }

  public UserPortfolioItemSummaryModelResponse fetchUserPortfolioAsmtSummary(
      UserPortfolioItemSummaryCommand command) {

    this.command = command;
    contentSource = this.command.getContentSource();

    List<UserPortfolioItemSummaryModel> itemSummary = new ArrayList<>();
    List<UserPortfolioItemQuestionSummaryModel> questionModels = new ArrayList<>();

    if (contentSource.equalsIgnoreCase("dailyclassactivity")) {
      itemSummary = fetchCAItemsPerformance(this.command);
      questionModels = fetchCAItemsQuestionPerformance(this.command);
    } else if (contentSource.equalsIgnoreCase("coursemap")) {
      itemSummary = fetchItemsPerformance(this.command);
      questionModels = fetchItemsQuestionPerformance(this.command);
    } else if (contentSource.equalsIgnoreCase("ILactivity")) {
      itemSummary = fetchILItemsPerformance(this.command);
      questionModels = fetchILItemsQuestionPerformance(this.command);
    }

    List<UserPortfolioItemQuestionSummaryModel> questionSummary = generateQuestionSummary(questionModels);
    Map<String, Object> response = new HashMap<>();
    UserPortfolioItemSummaryModelResponse result = new UserPortfolioItemSummaryModelResponse();
    response.put("assessment", itemSummary);
    response.put("questions", questionSummary);
    result.getContent(response);
    return result;

  }

  private List<UserPortfolioItemQuestionSummaryModel> generateQuestionSummary(
      List<UserPortfolioItemQuestionSummaryModel> models) {
    List<UserPortfolioItemQuestionSummaryModel> items = new ArrayList<>();

    List<String> contentIds = new ArrayList<>();
    models.forEach(model -> {
      contentIds.add(model.getId());
    });
    
    Map<String, CoreContentsModel> contentTitles =
        this.coreContentsService.fetchContentTitles(contentIds);
    models.forEach(model -> {
      if (contentTitles != null) {
        CoreContentsModel coreModel = contentTitles.get(model.getId());
        if (coreModel != null && coreModel.getTitle() != null) {
          model.setTitle(coreModel.getTitle());
          model.setResourceType(coreModel.getType());
        }
      }
      if (model.getQuestionType().equalsIgnoreCase("OE")) {
        Boolean isGradedObj = null;
        if (contentSource.equalsIgnoreCase("dailyclassactivity")) {
          isGradedObj =
              userPortfolioItemSummaryDao.fetchCAItemIsGraded(command.asBean(), model.getId());
        } else {
          isGradedObj =
              userPortfolioItemSummaryDao.fetchItemIsGraded(command.asBean(), model.getId());
        }
        if (isGradedObj != null && (isGradedObj)) {
          model.setIsGraded(true);
        } else {
          model.setIsGraded(false);
        }
      }
      
      Integer reaction = null;
      if (contentSource.equalsIgnoreCase("dailyclassactivity")) {
        reaction = userPortfolioItemSummaryDao
            .fetchUserCAAsmtQuestionReactionSummary(command.asBean(), model.getId());
      } else {
        reaction = userPortfolioItemSummaryDao
            .fetchUserAsmtQuestionReactionSummary(command.asBean(), model.getId());
      }
      model.setReaction(reaction != null ? reaction : 0);
      
      items.add(model);
    });
    return items;
  }

  private List<UserPortfolioItemQuestionSummaryModel> fetchItemsQuestionPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserAsmtQuestionSummary(command.asBean());
  }
  
  private List<UserPortfolioItemQuestionSummaryModel> fetchCAItemsQuestionPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserCAAsmtQuestionSummary(command.asBean());
  }
  
  private List<UserPortfolioItemQuestionSummaryModel> fetchILItemsQuestionPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserILAsmtQuestionSummary(command.asBean());
  }
  
  private List<UserPortfolioItemSummaryModel> fetchItemsPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserAsmtSummary(command.asBean());
  }
  
  private List<UserPortfolioItemSummaryModel> fetchCAItemsPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserCAAsmtSummary(command.asBean());
  }
  
  private List<UserPortfolioItemSummaryModel> fetchILItemsPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserILAsmtSummary(command.asBean());
  }

}
