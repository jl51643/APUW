package com.apuw.lab1.apuw_lab1.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String url;
	@ManyToOne
	private User user;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Picture picture = (Picture) o;
		return id != null && Objects.equals(id, picture.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
