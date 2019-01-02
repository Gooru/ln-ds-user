package org.gooru.ds.user.processor.user.course.competency.report;

import java.util.List;

/**
 * @author szgooru on 17-Jul-2018
 */
public class UserCourseCompetencyReportModelResponse {

  private ContextResponseModel context;
  private List<DomainsResponseModel> classCompetencies;
  private List<DomainsResponseModel> domainCompetencies;

  public ContextResponseModel getContext() {
    return context;
  }

  public void setContext(ContextResponseModel context) {
    this.context = context;
  }

  public List<DomainsResponseModel> getClassCompetencies() {
    return classCompetencies;
  }

  public void setClassCompetencies(List<DomainsResponseModel> classCompetencies) {
    this.classCompetencies = classCompetencies;
  }

  public List<DomainsResponseModel> getDomainCompetencies() {
    return domainCompetencies;
  }

  public void setDomainCompetencies(List<DomainsResponseModel> domainCompetencies) {
    this.domainCompetencies = domainCompetencies;
  }

  static class ContextResponseModel {
    private String classId;
    private String courseId;
    private String filter;
    private Integer fromMonth;
    private Integer fromYear;
    private Integer toMonth;
    private Integer toYear;
    private String subjectCode;

    public String getClassId() {
      return classId;
    }

    public void setClassId(String classId) {
      this.classId = classId;
    }

    public String getCourseId() {
      return courseId;
    }

    public void setCourseId(String courseId) {
      this.courseId = courseId;
    }

    public String getFilter() {
      return filter;
    }

    public void setFilter(String filter) {
      this.filter = filter;
    }

    public Integer getFromMonth() {
      return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
      this.fromMonth = fromMonth;
    }

    public Integer getFromYear() {
      return fromYear;
    }

    public void setFromYear(Integer fromYear) {
      this.fromYear = fromYear;
    }

    public Integer getToMonth() {
      return toMonth;
    }

    public void setToMonth(Integer toMonth) {
      this.toMonth = toMonth;
    }

    public Integer getToYear() {
      return toYear;
    }

    public void setToYear(Integer toYear) {
      this.toYear = toYear;
    }

    public String getSubjectCode() {
      return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
      this.subjectCode = subjectCode;
    }
  }

  static class DomainsResponseModel {
    private String domainCode;
    private String domainName;
    private Integer domainSeq;
    private List<DomainCompetenciesResponseModel> competencies;

    public String getDomainCode() {
      return domainCode;
    }

    public void setDomainCode(String domainCode) {
      this.domainCode = domainCode;
    }

    public String getDomainName() {
      return domainName;
    }

    public void setDomainName(String domainName) {
      this.domainName = domainName;
    }

    public Integer getDomainSeq() {
      return domainSeq;
    }

    public void setDomainSeq(Integer domainSeq) {
      this.domainSeq = domainSeq;
    }

    public List<DomainCompetenciesResponseModel> getCompetencies() {
      return competencies;
    }

    public void setCompetencies(List<DomainCompetenciesResponseModel> competencies) {
      this.competencies = competencies;
    }
  }

  static class DomainCompetenciesResponseModel {
    private String competencyCode;
    private String competencyName;
    private String competencyDesc;
    private String competencyStudentDesc;
    private Integer competencySeq;

    public String getCompetencyCode() {
      return competencyCode;
    }

    public void setCompetencyCode(String competencyCode) {
      this.competencyCode = competencyCode;
    }

    public String getCompetencyName() {
      return competencyName;
    }

    public void setCompetencyName(String competencyName) {
      this.competencyName = competencyName;
    }

    public String getCompetencyDesc() {
      return competencyDesc;
    }

    public void setCompetencyDesc(String competencyDesc) {
      this.competencyDesc = competencyDesc;
    }

    public String getCompetencyStudentDesc() {
      return competencyStudentDesc;
    }

    public void setCompetencyStudentDesc(String competencyStudentDesc) {
      this.competencyStudentDesc = competencyStudentDesc;
    }

    public Integer getCompetencySeq() {
      return competencySeq;
    }

    public void setCompetencySeq(Integer competencySeq) {
      this.competencySeq = competencySeq;
    }
  }

  static class CompetenciesResponseModel {
    private String competencyCode;
    private Integer status;

    public String getCompetencyCode() {
      return competencyCode;
    }

    public void setCompetencyCode(String competencyCode) {
      this.competencyCode = competencyCode;
    }

    public Integer getStatus() {
      return status;
    }

    public void setStatus(Integer status) {
      this.status = status;
    }

  }
}
