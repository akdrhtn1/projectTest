package com.todotogether.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class MemberDto implements Serializable {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(regexp = "/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/i",
            message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    private String password2;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "/^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$/", message = "전화번호 형식에 맞게 입력해주세요")
    private String phone;

    @NotEmpty(message = "백업 이메일은 필수 입력 값입니다.")
    @Email(regexp = "/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/i",
            message = "이메일 형식으로 입력해주세요.")
    private String backupEmail;

    private String profile;
}
