package org.gooru.ds.user.processor.user.course.competency.report;

/**
 * @author szgooru on 18-Jul-2018
 */
public class DomainCompetenciesModel {

	private String domainCode;
	private String domainName;
	private Integer domainSeq;
	private String competencyCode;
	private String competencyName;
	private String competencyDesc;
	private String competencyStudentDesc;
	private Integer competencySeq;

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
