package eu.trentorise.game.challenges.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "modelName", "fields", "start", "end",
		"completed", "dateCompleted" })
public class ChallengeConcept {

	@JsonProperty("name")
	private String name;
	@JsonProperty("modelName")
	private String modelName;
	@JsonProperty("fields")
	@JsonDeserialize(as = LinkedHashMap.class)
	private Map<String, Object> fields = new LinkedHashMap<String, Object>();
	@JsonProperty("start")
	private Long start;
	@JsonProperty("end")
	private Long end;
	@JsonProperty("completed")
	private boolean completed;
	@JsonProperty("dateCompleted")
	private Long dateCompleted;

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("fields")
	public Map<String, Object> getFields() {
		return fields;
	}

	@JsonProperty("fields")
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	@JsonProperty("modelName")
	public String getModelName() {
		return modelName;
	}

	@JsonProperty("modelName")
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@JsonProperty("start")
	public Long getStart() {
		return start;
	}

	@JsonProperty("start")
	public void setStart(Long start) {
		this.start = start;
	}

	@JsonProperty("end")
	public Long getEnd() {
		return end;
	}

	@JsonProperty("end")
	public void setEnd(Long end) {
		this.end = end;
	}

	@JsonProperty("completed")
	public boolean getCompleted() {
		return completed;
	}

	@JsonProperty("completed")
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@JsonProperty("dateCompleted")
	public Long getDateCompleted() {
		return dateCompleted;
	}

	@JsonProperty("dateCompleted")
	public void setDateCompleted(Long dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

}
