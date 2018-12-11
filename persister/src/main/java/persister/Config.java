package persister;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the configs database table.
 * 
 */
@Entity
@Table(name="configs")
@NamedQuery(name="Config.findAll", query="SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="config_id")
	private Integer configId;

	private String area;

	private String config;

	private Integer sort;

	private String value;

	public Config() {
	}

	public Integer getConfigId() {
		return this.configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}