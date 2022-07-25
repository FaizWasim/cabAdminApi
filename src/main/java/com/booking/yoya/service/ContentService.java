package com.booking.yoya.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.booking.yoya.constant.NumbersConstant;
import com.booking.yoya.constant.StringsConstant;
import com.booking.yoya.dao.CityDao;
import com.booking.yoya.dao.ContentDao;
import com.booking.yoya.dao.CountryDao;
import com.booking.yoya.dao.ProcessDao;
import com.booking.yoya.dao.StateDao;
import com.booking.yoya.dao.ContentDao;
import com.booking.yoya.dto.ContentDto;
import com.booking.yoya.model.City;
import com.booking.yoya.model.Country;
import com.booking.yoya.model.Process;
import com.booking.yoya.model.State;
import com.booking.yoya.model.Content;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.service.aws.s3.AmazonS3ClientService;

@Service
public class ContentService {

	@Autowired
	private ContentDao contentDao;

	@Autowired
	private AmazonS3ClientService amazonS3ClientService;

	public BaseMessageResponse<Object> createNewContent(ContentDto contentDto) {
		try {

			System.out.println("contentDto ---" + contentDto.toString());
			Content content = new Content();
			content.setContentTitle(contentDto.getContentTitle());
			content.setContentDescription(contentDto.getContentDescription());

			if (!contentDto.getContentImage().isEmpty()) {
				MultipartFile icon = contentDto.getContentImage();
				String folder = StringsConstant.CONTENT.value;
				UUID randomUUID = UUID.randomUUID();

				boolean status = this.amazonS3ClientService.uploadFileToS3Bucket(icon, true, folder, randomUUID);
				Map<String, String> response = new HashMap();

				if (status) {
					String path = folder + randomUUID + icon.getOriginalFilename();
					content.setContentImage(path);
				}

			} else {
				content.setContentImage(StringsConstant.EMPTY.value);
			}
			System.out.println("content ---" + content.toString());
			Content savedContent = contentDao.save(content);

			return BaseMessageResponse.builder().message("success").status(true).data(savedContent).build();

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build();

		}
	}

	public BaseMessageResponse<Object> getAllContents() {

		try {
			List<Content> contentList = contentDao.findAll();
			if (contentList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(contentList).build();
			} else {
				List<ContentDto> contentDtoList = contentList.stream().map(new Function<Content, ContentDto>() {
					@Override
					public ContentDto apply(Content s) {
						ContentDto sDto = new ContentDto();

						sDto.setContentDescription(s.getContentDescription());
						sDto.setContentId(s.getContentId());
						sDto.setContentImagePath(StringsConstant.S3URL.value + s.getContentImage());
						sDto.setContentTitle(s.getContentTitle());

						return sDto;
					}
				}).collect(Collectors.toList());

				return BaseMessageResponse.builder().message("success").status(true).data(contentDtoList).build();

			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();
		}

	}

	public BaseMessageResponse<Object> deleteContent(Long contentId) {

		try {
			Optional<Content> content = contentDao.findById(contentId);

			if (!content.isPresent()) {
				return BaseMessageResponse.builder().message("content not present").status(false).data(content).build();

			} else {
				contentDao.deleteById(content.get().getContentId());
				return BaseMessageResponse.builder().message("success").status(true).data(content).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("Something went wrong").status(false).data(e.getMessage())
					.build();
		}

	}

	public BaseMessageResponse<Object> updateContent(ContentDto content) {
		Content oldContent = contentDao.findById(content.getContentId()).get();
		try {
			if (content.getContentId() != null) {

				if (content.getContentTitle().isEmpty())
					oldContent.setContentTitle(oldContent.getContentTitle());
				else
					oldContent.setContentTitle(content.getContentTitle());
				if (content.getContentDescription().isEmpty())
					oldContent.setContentDescription(oldContent.getContentDescription());
				else
					oldContent.setContentDescription(content.getContentDescription());

				if (!content.getContentImage().isEmpty()) {
					MultipartFile icon = content.getContentImage();
					String folder = StringsConstant.CONTENT.value;
					UUID randomUUID = UUID.randomUUID();

					boolean status = this.amazonS3ClientService.uploadFileToS3Bucket(icon, true, folder, randomUUID);
					Map<String, String> response = new HashMap();

					if (status) {
						String path = folder  + randomUUID + icon.getOriginalFilename();
						oldContent.setContentImage(path);
					}

				} else {
					oldContent.setContentImage(oldContent.getContentImage());
				}
				Content savedContent = contentDao.save(oldContent);

				return BaseMessageResponse.builder().message("success").status(true).data(savedContent).build();
			} else {
				return BaseMessageResponse.builder().message("content not present").status(false).data(content).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build();

		}

	}

	public BaseMessageResponse<Object> contentById(Long contentId) {
		try {

			Optional<Content> content = contentDao.findById(contentId);
			if (!content.isPresent()) {
				return BaseMessageResponse.builder().message("content not present").status(false).data(content).build();

			} else {
				ContentDto sDto = new ContentDto();

				sDto.setContentDescription(content.get().getContentDescription());
				sDto.setContentId(content.get().getContentId());
				sDto.setContentImagePath(StringsConstant.S3URL.value + content.get().getContentImage());
				sDto.setContentTitle(content.get().getContentTitle());

				return BaseMessageResponse.builder().message("success").status(true).data(sDto).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build();
		}
	}
}
