package org.gooru.ds.user.processor.atc.pvc.course.competency;

import java.util.UUID;

import org.gooru.ds.user.app.jdbi.DBICreator;
import org.skife.jdbi.v2.DBI;

/**
 * @author ashish.
 */
interface SubjectInferer {

    String inferSubjectForCourse(UUID courseId);

    static SubjectInferer build() {
    	return new SubjectInfererImpl(DBICreator.getDbiForCoreDS());
        //return new SubjectInfererImpl(DBICreator.getDbiForDefaultDS());
    }

    static SubjectInferer build(DBI dbi) {
        return new SubjectInfererImpl(dbi);
    }
}
