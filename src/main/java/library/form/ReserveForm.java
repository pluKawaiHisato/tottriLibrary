package library.form;

import javax.validation.constraints.Pattern;

public class ReserveForm {
	@Pattern(regexp = "[a-zA-Z0-9]*", message="ユーザーIDは半角英数字で入力してください" )
	private String userId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
