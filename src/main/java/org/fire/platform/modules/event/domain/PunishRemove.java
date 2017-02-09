package org.fire.platform.modules.event.domain;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author ZKT
 * @date 2017-1-18 17:32:53
 */
 
@SuppressWarnings("serial")
public class PunishRemove extends BaseEntity {
	// Fields
		private Long punishEventId;
		private Long id;
		private String decisionNumber;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date removeTime;
		private String basis;
		private String checkSituation;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date createTime;
	public PunishRemove() {
		this.clear();
	}
	public PunishRemove(Long id) {
		this();

		this.id=id;
	}
	
	public void clear() {
		punishEventId=null;
		id=null;
		decisionNumber=null;
		removeTime=null;
		basis=null;
		checkSituation=null;
		createTime=null;
	}

	// Getters/Setters
		public Long getPunishEventId(){
			return punishEventId;
		}
		public void setPunishEventId(Long punishEventId){
			this.punishEventId=punishEventId;
		}
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getDecisionNumber(){
			return decisionNumber;
		}
		public void setDecisionNumber(String decisionNumber){
			this.decisionNumber=decisionNumber;
		}
		public Date getRemoveTime(){
			return removeTime;
		}
		public void setRemoveTime(Date removeTime){
			this.removeTime=removeTime;
		}
		public String getBasis(){
			return basis;
		}
		public void setBasis(String basis){
			this.basis=basis;
		}
		public String getCheckSituation(){
			return checkSituation;
		}
		public void setCheckSituation(String checkSituation){
			this.checkSituation=checkSituation;
		}
		public Date getCreateTime(){
			return createTime;
		}
		public void setCreateTime(Date createTime){
			this.createTime=createTime;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PunishRemove=[");
		builder.append("punishEventId="+punishEventId+",");
		builder.append("id="+id+",");
		builder.append("decisionNumber="+decisionNumber+",");
		builder.append("removeTime="+removeTime+",");
		builder.append("basis="+basis+",");
		builder.append("checkSituation="+checkSituation+",");
		builder.append("createTime="+createTime+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
