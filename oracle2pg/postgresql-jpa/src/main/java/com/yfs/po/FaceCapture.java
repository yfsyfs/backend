package com.yfs.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_face_capture")
public class FaceCapture {

	@Id
	@GeneratedValue
	private Long id;

	private Long cid;

	@Column(name = "device_name")
	private String deviceName;

	@Column(name = "capture_time")
	private Long captureTime;

	@Column(name = "capture_address")
	private String captureAddress;

	private Double longitude;

	private Double latitude;

	@Lob
	@Column(name = "scene_stream")
	private byte[] sceneStream;

	@Lob
	@Column(name = "small_stream")
	private byte[] smallStream;

}
