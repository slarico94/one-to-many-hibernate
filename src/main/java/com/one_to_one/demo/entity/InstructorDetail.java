package com.one_to_one.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "instructor_detail")
@Data
@NoArgsConstructor
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instructor_detail_id")
	private Integer instructorDetailId;

	@Column(name = "youtube_channel")
	private String youtubeChannel;

	@Column(name = "hobby")
	private String hobby;
	
	@OneToOne(mappedBy = "instructorDetail", 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@ToString.Exclude
	private Instructor instructor;

	public InstructorDetail(Integer instructorDetailId, String youtubeChannel, String hobby) {
		super();
		this.instructorDetailId = instructorDetailId;
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}
	
	
}
