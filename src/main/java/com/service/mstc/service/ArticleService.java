package com.service.mstc.service;

import com.service.mstc.dto.PageStatusDto;
import com.service.mstc.dto.common.DataListDto;
import com.service.mstc.model.Article;

public interface ArticleService {
  Article save(Article article);

  Article update(Article article);

  void delete(String id);

  Article getBySlug(String slug);

  DataListDto get(PageStatusDto pageableDto);

  Article getById(String id);
}
