package springboot.example.pojo;

import javax.validation.constraints.Min;

public class TodoPojo {

	/**
	 * 私有 id，是代表项目任务/非项目任务/风险/问题/评审待办问题等多张表的外键
	 */
	@Min(value = 1, message = "{todo.privateId.min}")
	private int privateId;
	/**
	 * 创建者id
	 */
	@Min(value = 1, message = "{creatorId}")
	private int creatorId;

	public int getPrivateId() {
		return privateId;
	}

	public void setPrivateId(int privateId) {
		this.privateId = privateId;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

}
