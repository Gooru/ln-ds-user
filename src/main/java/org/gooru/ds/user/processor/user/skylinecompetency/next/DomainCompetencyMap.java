package org.gooru.ds.user.processor.user.skylinecompetency.next;

import java.util.List;

interface DomainCompetencyMap {

  public List<String> getDomains();

  /*
   * Fetch the Competency Model for this Domain
   */
  public List<CompetencyModel> getCompetenciesForDomain(String domainCode);

  /*
   * Fetch Only the Competency Codes from the Competency Model for this Domain
   */
  public List<String> getDomainCompetencyCodes(String domainCode);

  static DomainCompetencyMap create(List<CompetencyModel> competencies) {
    return new DomainCompetencyMapImpl(competencies);
  }

}
