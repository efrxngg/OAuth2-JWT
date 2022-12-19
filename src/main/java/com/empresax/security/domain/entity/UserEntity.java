package com.empresax.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "varchar(16) not null")
    private String userId;

    @NotNull(message = "User name is required.")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private RoleType role;

    @Column(name = "user_state")
    @Enumerated(value = EnumType.ORDINAL)
    private StateType userStatus = StateType.ACTIVE;

}

@Converter(autoApply = true)
class RoleConverter implements AttributeConverter<RoleType, String> {

    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        return attribute.getAuthority();
    }

    @JsonCreator
    @Override
    public RoleType convertToEntityAttribute(@JsonProperty String dbData) {
        return RoleType.fromAuthority(dbData);
    }

}