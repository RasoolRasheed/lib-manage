package com.rasool.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Rasool
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Book> borrowedBooks;

}
