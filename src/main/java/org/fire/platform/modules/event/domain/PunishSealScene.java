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
public class PunishSealScene extends BaseEntity {
	// Fields
		private Long id;
		private Long punishEventId;
		private String decisionNumber;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date entryTime;
		private String basis;
		private String sceneRange;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date createTime;
	public PunishSealScene() {
		this.clear();
	}
	public PunishSealScene(Long id) {
		this();

		this.id=id;
	}
	
	public void clear() {
		id=null;
		punishEventId=null;
		decisionNumber=null;
		entryTime=null;
		basis=null;
		sceneRange=null;
		createTime=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getPunishEventId(){
			return punishEventId;
		}
		public void setPunishEventId(Long punishEventId){
			this.punishEventId=punishEventId;
		}
		public String getDecisionNumber(){
			return decisionNumber;
		}
		public void setDecisionNumber(String decisionNumber){
			this.decisionNumber=decisionNumber;
		}
		public Date getEntryTime(){
			return entryTime;
		}
		public void setEntryTime(Date entryTime){
			this.entryTime=entryTime;
		}
		public String getBasis(){
			return basis;
		}
		public void setBasis(String basis){
			this.basis=basis;
		}
		public String getSceneRange(){
			return sceneRange;
		}
		public void setSceneRange(String sceneRange){
			this.sceneRange=sceneRange;
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
		builder.append("PunishSealScene=[");
		builder.append("id="+id+",");
		builder.append("punishEventId="+punishEventId+",");
		builder.append("decisionNumber="+decisionNumber+",");
		builder.append("entryTime="+entryTime+",");
		builder.append("basis="+basis+",");
		builder.append("sceneRange="+sceneRange+",");
		builder.append("createTime="+createTime+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
