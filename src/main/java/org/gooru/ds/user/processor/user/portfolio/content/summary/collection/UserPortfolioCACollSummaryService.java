package org.gooru.ds.user.processor.user.portfolio.content.summary.collection;

import java.sql.Timestamp;
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
class UserPortfolioCACollSummaryService {

  private final UserPortfolioItemSummaryDao userPortfolioItemSummaryDao;
  private final CoreContentsService coreContentsService;
  private UserPortfolioItemSummaryCommand command;
  private String contentSource;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserPortfolioCACollSummaryService.class);

  UserPortfolioCACollSummaryService(DBI dbi, DBI coreDbi) {
    this.userPortfolioItemSummaryDao = dbi.onDemand(UserPortfolioItemSummaryDao.class);
    this.coreContentsService = new CoreContentsService(coreDbi);
  }

  public UserPortfolioItemSummaryModelResponse fetchUserPortfolioCollSummary(
      UserPortfolioItemSummaryCommand command) {

    this.command = command;
    contentSource = this.command.getContentSource();

    UserPortfolioItemSummaryModel itemSummary = new UserPortfolioItemSummaryModel();
    List<UserPortfolioItemCollResAggModel> questionModels = new ArrayList<>();

    if (contentSource.equalsIgnoreCase("dailyclassactivity")) {
      itemSummary = fetchCAItemsPerformance(this.command);
      questionModels = fetchCAItemsQuestionPerformance(this.command);
    }

    List<UserPortfolioItemResSummaryModelResponse> questionSummary = generateQuestionSummary(questionModels);
    Map<String, Object> response = new HashMap<>();
    UserPortfolioItemSummaryModelResponse result = new UserPortfolioItemSummaryModelResponse();
    response.put("collection", itemSummary);
    response.put("resources", questionSummary);
    result.setContent(response);
    return result;

  }

  private List<UserPortfolioItemResSummaryModelResponse> generateQuestionSummary(
      List<UserPortfolioItemCollResAggModel> resAggModels) {
    List<UserPortfolioItemResSummaryModelResponse> items = new ArrayList<>();

    List<String> contentIds = new ArrayList<>();
    resAggModels.forEach(model -> {
      contentIds.add(model.getId());
    });
    
    Map<String, CoreContentsModel> contentTitles =
        this.coreContentsService.fetchContentTitles(contentIds);
    resAggModels.forEach(model -> {
      UserPortfolioItemResSummaryModelResponse quesResponse = new UserPortfolioItemResSummaryModelResponse(); 
      if (contentTitles != null) {
        CoreContentsModel coreModel = contentTitles.get(model.getId());
        if (coreModel != null && coreModel.getTitle() != null) {
          quesResponse.setTitle(coreModel.getTitle());
        }
      }
      quesResponse.setId(model.getId());
      quesResponse.setAnswerObject(model.getAnswerObject() != null ? model.getAnswerObject().getList() : null);
      quesResponse.setViews(model.getAttempts());
      quesResponse.setQuestionType(model.getQuestionType());
      quesResponse.setReaction(model.getReaction());
      quesResponse.setScore(model.getScore());
      quesResponse.setResourceType(model.getResourceType());
      quesResponse.setTimespent(model.getTimespent());
      if (quesResponse.getResourceType().equalsIgnoreCase("question")) {
        quesResponse.setAnswerStatus("skipped");
      }
      
      UserPortfolioItemCollResScoreModel aggscore = userPortfolioItemSummaryDao.fetchCACollResourceAggScore(command.asBean(), model.getId());
      if (aggscore != null) {
        quesResponse.setScore(aggscore.getScore());
        quesResponse.setMaxScore(aggscore.getMaxScore());
        quesResponse.setAnswerObject(aggscore.getAnswerObject() != null ? aggscore.getAnswerObject().getList() : null);
        quesResponse.setAnswerStatus(aggscore.getAttemptStatus());
      }
      if (model.getQuestionType().equalsIgnoreCase("OE")) {
        Boolean isGradedObj =
              userPortfolioItemSummaryDao.fetchCACollResourceGradeStatus(command.asBean(), model.getId());
        if (isGradedObj != null && (isGradedObj)) {
          quesResponse.setIsGraded(true);
        } else {
          quesResponse.setIsGraded(false);
        }
      }
      
      Integer reaction = null;
      if (contentSource.equalsIgnoreCase("dailyclassactivity")) {
        reaction = userPortfolioItemSummaryDao.fetchCACollResourceReaction(command.asBean(), model.getId());
      }
      quesResponse.setReaction(reaction != null ? reaction : 0);
      
      items.add(quesResponse);
    });
    return items;
  }

  private List<UserPortfolioItemCollResAggModel> fetchCAItemsQuestionPerformance(UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchCACollResourceAgg(command.asBean());
  }

  private UserPortfolioItemSummaryModel fetchCAItemsPerformance(UserPortfolioItemSummaryCommand command) {
    Double maxScore = userPortfolioItemSummaryDao.fetchCACollMaxScore(command.asBean());
    Timestamp lastAssessed = userPortfolioItemSummaryDao.fetchCACollLastAccessed(command.asBean());
    UserPortfolioItemCollTimespentModel timeSpent = userPortfolioItemSummaryDao.fetchCACollTimespent(command.asBean());
    Integer reaction = userPortfolioItemSummaryDao.fetchCACollReaction(command.asBean());
    Double score = userPortfolioItemSummaryDao.fetchCACollScore(command.asBean());
    UserPortfolioItemSummaryModel coll = null;
    if (lastAssessed != null) {
      coll = new UserPortfolioItemSummaryModel();
      coll.setId(command.getItemId());
      coll.setEventTime(lastAssessed);
      coll.setReaction(reaction);
      coll.setTimespent(timeSpent != null ? timeSpent.getTimespent() : 0L);
      coll.setScore(score);
      coll.setViews(timeSpent != null ? timeSpent.getAttempts() : 0L);
      coll.setMaxScore(maxScore);
      coll.setType(timeSpent != null ? timeSpent.getCollectionType() : "NA");
    }
    return coll;
  }

}
