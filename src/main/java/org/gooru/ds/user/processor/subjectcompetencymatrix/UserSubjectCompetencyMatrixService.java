package org.gooru.ds.user.processor.subjectcompetencymatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;


class UserSubjectCompetencyMatrixService {

  private final UserSubjectCompetencyMatrixDao userSubjectCompetencyMatrixDao;
  private final String USER_SUBJECT_COMPETENCY_MATRIX = "userSubjectCompetencyMatrix";

  UserSubjectCompetencyMatrixService(DBI dbi) {
    this.userSubjectCompetencyMatrixDao = dbi.onDemand(UserSubjectCompetencyMatrixDao.class);
  }

  private Map<String, List<SubjectCompetencyMatrixResponseModel>> userSubjectCompetencyMatrix(
      List<SubjectCompetencyMatrixModel> userSubjectCompetencyMatrixModels) {
    List<SubjectCompetencyMatrixResponseModel> listSubjectCompetencyMatrix = new ArrayList<>();
    Map<String, List<SubjectCompetencyMatrixResponseModel>> subjectCompetecyMatrix =
        new HashMap<>();
    if (userSubjectCompetencyMatrixModels != null && !userSubjectCompetencyMatrixModels.isEmpty()) {
      Map<String, SubjectCompetencyMatrixResponseModel> subjectCompetencyMatrixModelMap =
          groupCompetencyMatrixBySubject(userSubjectCompetencyMatrixModels);
      subjectCompetencyMatrixModelMap.forEach((subjectCode, subjectCompetencyMatrix) -> {
        listSubjectCompetencyMatrix.add(subjectCompetencyMatrix);
      });
      Collections.sort(listSubjectCompetencyMatrix,
          Comparator.comparing(SubjectCompetencyMatrixResponseModel::getClassificationSeq)
              .thenComparing(SubjectCompetencyMatrixResponseModel::getSubjectSeq));
    }
    subjectCompetecyMatrix.put(USER_SUBJECT_COMPETENCY_MATRIX, listSubjectCompetencyMatrix);

    return subjectCompetecyMatrix;
  }

  private Map<String, SubjectCompetencyMatrixResponseModel> groupCompetencyMatrixBySubject(
      List<SubjectCompetencyMatrixModel> userSubjectCompetencyMatrixModels) {
    Map<String, SubjectCompetencyMatrixResponseModel> userCompetencyMatrixModelMap =
        new HashMap<>();
    userSubjectCompetencyMatrixModels.forEach(subjectCompetencyStatsModel -> {
      String subjectCode = subjectCompetencyStatsModel.getSubjectCode();
      CompetencyResponseModel competencyMatrix = new CompetencyResponseModel();
      competencyMatrix.setCompetencyCount(subjectCompetencyStatsModel.getCompetencyCount());
      competencyMatrix.setCompetencyStatus(subjectCompetencyStatsModel.getCompetencyStatus());

      if (userCompetencyMatrixModelMap.get(subjectCode) != null) {
        SubjectCompetencyMatrixResponseModel subjectCompetencyStats =
            userCompetencyMatrixModelMap.get(subjectCode);
        subjectCompetencyStats.getCompetencyStats().add(competencyMatrix);
      } else {
        List<CompetencyResponseModel> listCompetencyStats = new ArrayList<>();
        listCompetencyStats.add(competencyMatrix);
        SubjectCompetencyMatrixResponseModel subjectCompetencyStats =
            new SubjectCompetencyMatrixResponseModel();
        subjectCompetencyStats.setSubjectCode(subjectCompetencyStatsModel.getSubjectCode());
        subjectCompetencyStats.setSubjectName(subjectCompetencyStatsModel.getSubjectName());
        subjectCompetencyStats.setSubjectSeq(subjectCompetencyStatsModel.getSubjectSeq());
        subjectCompetencyStats
            .setClassificationCode(subjectCompetencyStatsModel.getClassificationCode());
        subjectCompetencyStats
            .setClassificationName(subjectCompetencyStatsModel.getClassificationName());
        subjectCompetencyStats
            .setClassificationSeq(subjectCompetencyStatsModel.getClassificationSeq());
        subjectCompetencyStats.setCompetencyStats(listCompetencyStats);
        userCompetencyMatrixModelMap.put(subjectCode, subjectCompetencyStats);
      }
    });
    return userCompetencyMatrixModelMap;
  }

  Map<String, List<SubjectCompetencyMatrixResponseModel>> fetchUserSubjectCompetencyMatrix(
      UserSubjectCompetencyMatrixCommand command) {
    final List<SubjectCompetencyMatrixModel> learnerSubjectCompetencyStatsModels =
        userSubjectCompetencyMatrixDao.fetchUserSubjectCompetencyMatrix(command.asBean());
    return userSubjectCompetencyMatrix(learnerSubjectCompetencyStatsModels);
  }
}
