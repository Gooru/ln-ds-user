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
class UserPortfolioCollSummaryService {

  private final UserPortfolioItemSummaryDao userPortfolioItemSummaryDao;
  private final CoreContentsService coreContentsService;
  private UserPortfolioItemSummaryCommand command;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(UserPortfolioCollSummaryService.class);

  UserPortfolioCollSummaryService(DBI dbi, DBI coreDbi) {
    this.userPortfolioItemSummaryDao = dbi.onDemand(UserPortfolioItemSummaryDao.class);
    this.coreContentsService = new CoreContentsService(coreDbi);
  }

  public UserPortfolioItemSummaryModelResponse fetchUserPortfolioCollSummary(
      UserPortfolioItemSummaryCommand command) {
    this.command = command;

    UserPortfolioItemSummaryModel itemSummary = new UserPortfolioItemSummaryModel();
    List<UserPortfolioItemCollResAggModel> questionModels = new ArrayList<>();

    itemSummary = fetchItemsPerformance(this.command);
    questionModels = fetchItemsQuestionPerformance(this.command);

    List<UserPortfolioItemResSummaryModelResponse> questionSummary =
        generateQuestionSummary(questionModels);
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
    resAggModels.forEach(resModel -> {
      UserPortfolioItemResSummaryModelResponse resourceResponse =
          new UserPortfolioItemResSummaryModelResponse();
      if (contentTitles != null) {
        CoreContentsModel coreModel = contentTitles.get(resModel.getId());
        if (coreModel != null && coreModel.getTitle() != null) {
          resourceResponse.setTitle(coreModel.getTitle());
        }
      }
      resourceResponse.setId(resModel.getId());
      resourceResponse.setAnswerObject(
          resModel.getAnswerObject() != null ? resModel.getAnswerObject().getList() : null);
      resourceResponse.setViews(resModel.getAttempts());
      resourceResponse.setQuestionType(resModel.getQuestionType());
      resourceResponse.setReaction(resModel.getReaction());
      resourceResponse.setScore(resModel.getScore());
      resourceResponse.setResourceType(resModel.getResourceType());
      resourceResponse.setTimespent(resModel.getTimespent());
      if (resourceResponse.getResourceType().equalsIgnoreCase("question")) {
        resourceResponse.setAnswerStatus("skipped");
      }

      UserPortfolioItemCollResScoreModel aggscore =
          userPortfolioItemSummaryDao.fetchCollResourceAggScore(command.asBean(), resModel.getId());
      if (aggscore != null) {
        resourceResponse.setScore(aggscore.getScore());
        resourceResponse.setMaxScore(aggscore.getMaxScore());
        resourceResponse.setAnswerObject(
            aggscore.getAnswerObject() != null ? aggscore.getAnswerObject().getList() : null);
        resourceResponse.setAnswerStatus(aggscore.getAttemptStatus());
      }
      if (resModel.getQuestionType().equalsIgnoreCase("OE")) {
        Boolean isGradedObj = userPortfolioItemSummaryDao
            .fetchCollResourceGradeStatus(command.asBean(), resModel.getId());
        if (isGradedObj != null && (isGradedObj)) {
          resourceResponse.setIsGraded(true);
        } else {
          resourceResponse.setIsGraded(false);
        }
      }

      Integer reaction =
          userPortfolioItemSummaryDao.fetchCollResourceReaction(command.asBean(), resModel.getId());
      resourceResponse.setReaction(reaction != null ? reaction : 0);

      items.add(resourceResponse);
    });
    return items;
  }

  private List<UserPortfolioItemCollResAggModel> fetchItemsQuestionPerformance(
      UserPortfolioItemSummaryCommand command) {
    return userPortfolioItemSummaryDao.fetchCollResourceAgg(command.asBean());
  }

  private UserPortfolioItemSummaryModel fetchItemsPerformance(
      UserPortfolioItemSummaryCommand command) {
    Double maxScore = userPortfolioItemSummaryDao.fetchCollMaxScore(command.asBean());
    Timestamp lastAssessed = userPortfolioItemSummaryDao.fetchCollLastAccessed(command.asBean());
    UserPortfolioItemCollTimespentModel timeSpent =
        userPortfolioItemSummaryDao.fetchCollTimespent(command.asBean());
    Integer reaction = userPortfolioItemSummaryDao.fetchCollReaction(command.asBean());
    Double score = userPortfolioItemSummaryDao.fetchCollScore(command.asBean());
    UserPortfolioItemSummaryModel coll = null;
    if (lastAssessed != null) {
      coll = new UserPortfolioItemSummaryModel();
      coll.setId(command.getItemId());
      coll.setEventTime(lastAssessed);
      coll.setReaction(reaction);
      coll.setScore(score);
      coll.setMaxScore(maxScore);
      if (timeSpent != null) {
        coll.setTimespent(timeSpent.getTimespent());
        coll.setViews(timeSpent.getAttempts());
        coll.setType(timeSpent.getCollectionType());
      }
    }
    return coll;
  }

}
