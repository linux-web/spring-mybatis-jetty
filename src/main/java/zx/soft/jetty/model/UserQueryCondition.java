package zx.soft.jetty.model;

/**
 * 用户查询条件
 * 
 * @author wanggang
 *
 */
public class UserQueryCondition {

	private long uid;
	private int gender;
	private String name;
	private String nick;

	public long getUid() {
		return uid;
	}

	public UserQueryCondition setUid(long uid) {
		this.uid = uid;
		return this;
	}

	public int getGender() {
		return gender;
	}

	public UserQueryCondition setGender(int gender) {
		this.gender = gender;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserQueryCondition setName(String name) {
		this.name = name;
		return this;
	}

	public String getNick() {
		return nick;
	}

	public UserQueryCondition setNick(String nick) {
		this.nick = nick;
		return this;
	}

}
