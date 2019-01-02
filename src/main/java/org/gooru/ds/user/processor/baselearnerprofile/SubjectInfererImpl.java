package org.gooru.ds.user.processor.baselearnerprofile;

import java.util.UUID;
import org.gooru.ds.user.processor.utils.ValidatorUtils;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish.
 */
class SubjectInfererImpl implements SubjectInferer {

  private final DBI dbi;
  private SubjectInfererDao dao;

  SubjectInfererImpl(DBI dbi) {
    this.dbi = dbi;
  }

  @Override
  public String inferSubjectForCourse(UUID courseId) {
    if (courseId != null) {
      String subjectCode = getSubjectInfererDao().fetchSubjectBucketForCourse(courseId);
      if (!ValidatorUtils.isNullOrEmpty(subjectCode)) {
        if (isFrameworkCode(subjectCode)) {
          subjectCode =
              getSubjectInfererDao().fetchGutSubjectCodeForFrameworkSubjectCode(subjectCode);
        }
        return subjectCode;
      }
    }
    return null;
  }

  private boolean isFrameworkCode(String subjectCode) {
    long countDots = subjectCode.chars().filter(ch -> ch == '.').count();
    return countDots > 1;
  }

  private SubjectInfererDao getSubjectInfererDao() {
    if (dao == null) {
      dao = dbi.onDemand(SubjectInfererDao.class);
    }
    return dao;
  }
}

