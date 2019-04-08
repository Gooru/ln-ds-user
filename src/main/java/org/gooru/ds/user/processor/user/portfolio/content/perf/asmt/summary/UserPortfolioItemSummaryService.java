package org.gooru.ds.user.processor.user.portfolio.content.perf.asmt.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.user.portfolio.content.perf.items.CoreContentsModel;
import org.gooru.ds.user.processor.user.portfolio.content.perf.items.CoreContentsService;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renuka
 */
class UserPortfolioItemSummaryService {

  private final UserPortfolioItemSummaryDao userPortfolioItemSummaryDao;
  private final CoreContentsService coreContentsService;
  private UserPortfolioItemSummaryCommand command;
  private String contentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioItemSummaryService.class);

  UserPortfolioItemSummaryService(DBI dbi, DBI coreDbi) {
    this.userPortfolioItemSummaryDao = dbi.onDemand(UserPortfolioItemSummaryDao.class);
    this.coreContentsService = new CoreContentsService(coreDbi);
  }

  public UserPortfolioItemSummaryModelResponse fetchUserPortfolioUniqueItemPerf(
      UserPortfolioItemSummaryCommand command) {

    this.command = command;
    contentSource = this.command.getContentSource();

    List<UserPortfolioItemSummaryModel> models = new ArrayList<>();
    List<UserPortfolioItemQuestionSummaryModel> questionModels = new ArrayList<>();

    if (contentSource != null && contentSource.equalsIgnoreCase("dailyclassactivity")) {
      models = fetchCAItemsPerformance(this.command);
      questionModels = fetchCAItemsQuestionPerformance(this.command);
    } else {
      questionModels = fetchItemsQuestionPerformance(this.command);
      models = fetchItemsPerformance(this.command);
    }

    List<UserPortfolioItemQuestionSummaryModel> userItems = generateResponse(questionModels);
    Map<String, Object> response = new HashMap<>();
    UserPortfolioItemSummaryModelResponse result = new UserPortfolioItemSummaryModelResponse();
    response.put("assessment", models);
    response.put("questions", userItems);
    result.getContent(response);
    return result;

  }

  private List<UserPortfolioItemQuestionSummaryModel> generateResponse(
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
          model.setType(coreModel.getContentType());
        }
      }
      if (model.getQuestionType().equalsIgnoreCase("OE")) {
        Boolean isGradedObj = null;
        if(contentSource != null && contentSource.equalsIgnoreCase("dailyclassactivity")) {
          isGradedObj = userPortfolioItemSummaryDao.fetchItemIsGraded(command.asBean(), model.getId());
        } else {
          isGradedObj = userPortfolioItemSummaryDao.fetchCAItemIsGraded(command.asBean(), model.getId());
        }
    if (isGradedObj != null && (isGradedObj)) {
      model.setIsGraded(true);
    } else {
      model.setIsGraded(false);
    }
  }
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
  
  private List<UserPortfolioItemSummaryModel> fetchItemsPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserAsmtSummary(command.asBean());
  }
  
  private List<UserPortfolioItemSummaryModel> fetchCAItemsPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchUserCAAsmtSummary(command.asBean());
  }


}
