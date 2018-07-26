package org.gooru.ds.user.processor.atc.pvc;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;


/**
 * @author mukul@gooru
 */
interface ClassMembersDao {
	
	   @SqlQuery("select user_id from class_member where class_id = :classId and class_member_status = 'joined'")
		   List<String> fetchClassMembers (@Bind("classId") UUID classId);

}
