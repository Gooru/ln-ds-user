package org.gooru.ds.user.processor.atc.pvc.course.competency;

import java.util.List;
import org.gooru.ds.user.processor.atc.pvc.competency.CompetencyModel;
import org.gooru.ds.user.processor.atc.pvc.competency.DomainCode;
import org.gooru.ds.user.processor.atc.pvc.competency.DomainModel;
import org.gooru.ds.user.processor.atc.pvc.competency.SubjectCode;
import io.vertx.core.json.JsonObject;

/**
 * This is enriched representation of {@link org.gooru.route0.infra.data.competency.CompetencyRoute}
 * from DB. There is one important difference though. When
 * {@link org.gooru.route0.infra.data.competency.CompetencyRoute#getPathForDomain(DomainCode)} is
 * called, it gives path along with directionality which means both in order of progression and
 * reverse order of progression paths are served. However,
 * {@link CompetencyRouteModel#getPathForDomain(DomainCode)} does not serve the paths which are in
 * reverse order of progression.
 *
 * @author ashish.
 */
public interface CompetencyRouteModel {

  SubjectCode getSubject();

  List<DomainModel> getDomainsOrdered();

  List<CompetencyModel> getPathForDomain(DomainCode domainCode);

  JsonObject toJson();

}
