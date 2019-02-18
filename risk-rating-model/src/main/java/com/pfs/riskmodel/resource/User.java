package com.pfs.riskmodel.resource;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean status;
    private String userName;
    private String sapBPNumber;
    private String riskDepartment;
}
