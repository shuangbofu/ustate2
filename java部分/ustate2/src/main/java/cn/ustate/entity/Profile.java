package cn.ustate.entity;
/**
 * @author EricFu
 * @date 2017-7-14 下午7:54:46
 */
public class Profile {
	
	private int id;
	private String nickname;
	private String avatar;
	private int gender;
	
	public Profile() {
		super();
	}

	public Profile(int gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		if (nickname == null) {
			this.nickname = "某同学";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		if (avatar == null) {
			if (this.gender == 1) {
				this.avatar = "http://118.89.182.253/img/avatar_male.jpg";
			} else {
				this.avatar = "http://118.89.182.253/img/avatar_female.jpg";
			}
		}
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", nickname=" + nickname + ", avatar="
				+ avatar + ", gender=" + gender + "]";
	}
	
	
}
