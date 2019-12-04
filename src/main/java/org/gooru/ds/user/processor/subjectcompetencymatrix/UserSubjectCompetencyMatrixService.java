package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gooru.ds.user.processor.subjectcompetencymatrix.SubjectCompetencyMatrixModelMapper.MapperFields;
import org.skife.jdbi.v2.DBI;


class UserSubjectCompetencyMatrixService {

  private final UserSubjectCompetencyMatrixDao userSubjectCompetencyMatrixDao;
  private final String USER_SUBJECT_COMPETENCY_MATRIX = "user_subject_competency_matrix";

  UserSubjectCompetencyMatrixService(DBI dbi) {
    this.userSubjectCompetencyMatrixDao = dbi.onDemand(UserSubjectCompetencyMatrixDao.class);
  }

  private Map<String, List<Map<String, Object>>> userSubjectCompetencyMatrix(
      List<SubjectCompetencyMatrixModel> userSubjectCompetencyMatrixModels) {
    List<Map<String, Object>> listSubjectCompetencyMatrix = new ArrayList<>();
    Map<String, List<Map<String, Object>>> subjectCompetecyMatrix = new HashMap<>();
    if (userSubjectCompetencyMatrixModels != null
        && !userSubjectCompetencyMatrixModels.isEmpty()) {
      Map<String, Map<String, Object>> subjectCompetencyMatrixModelMap =
          groupCompetencyMatrixBySubject(userSubjectCompetencyMatrixModels);
      subjectCompetencyMatrixModelMap.forEach((subjectCode, subjectCompetencyMatrix) -> {
        listSubjectCompetencyMatrix.add(subjectCompetencyMatrix);
      });
    }
    subjectCompetecyMatrix.put(USER_SUBJECT_COMPETENCY_MATRIX, listSubjectCompetencyMatrix);

    return subjectCompetecyMatrix;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Map<String, Object>> groupCompetencyMatrixBySubject(
      List<SubjectCompetencyMatrixModel> userSubjectCompetencyMatrixModels) {
    Map<String, Map<String, Object>> userCompetencyMatrixModelMap = new HashMap<>();
    userSubjectCompetencyMatrixModels.forEach(subjectCompetencyStatsModel -> {
      String subjectCode = subjectCompetencyStatsModel.getSubjectCode();
      Map<String, Object> competencyMatrix = new HashMap<>();
      competencyMatrix.put(MapperFields.COMPETENCY_COUNT,
          subjectCompetencyStatsModel.getCompetencyCount());
      competencyMatrix.put(MapperFields.STATUS, subjectCompetencyStatsModel.getCompetencyStatus());
      if (userCompetencyMatrixModelMap.get(subjectCode) != null) {
        Map<String, Object> subjectCompetencyStats =
            userCompetencyMatrixModelMap.get(subjectCode);
        List<Map<String, Object>> listCompetencyStats =
            (List<Map<String, Object>>) subjectCompetencyStats.get(MapperFields.COMPETENCY_STATS);
        listCompetencyStats.add(competencyMatrix);
      } else {
        List<Map<String, Object>> listCompetencyStats = new ArrayList<>();
        listCompetencyStats.add(competencyMatrix);
        Map<String, Object> subjectCompetencyStats = new HashMap<>();
        subjectCompetencyStats.put(MapperFields.SUBJECT_CODE,
            subjectCompetencyStatsModel.getSubjectCode());
        subjectCompetencyStats.put(MapperFields.CLASSIFICATION_CODE,
            subjectCompetencyStatsModel.getClassificationCode());
        subjectCompetencyStats.put(MapperFields.SUBJECT_NAME, subjectCompetencyStatsModel.getSubjectName());
        subjectCompetencyStats.put(MapperFields.COMPETENCY_STATS, listCompetencyStats);
        userCompetencyMatrixModelMap.put(subjectCode, subjectCompetencyStats);
      }
    });
    return userCompetencyMatrixModelMap;
  }

  Map<String, List<Map<String, Object>>> fetchUserSubjectCompetencyMatrix(
      UserSubjectCompetencyMatrixCommand command) {
    final List<SubjectCompetencyMatrixModel> learnerSubjectCompetencyStatsModels =
        userSubjectCompetencyMatrixDao.fetchUserSubjectCompetencyMatrix(command.asBean());
    return userSubjectCompetencyMatrix(learnerSubjectCompetencyStatsModels);
  }
}
