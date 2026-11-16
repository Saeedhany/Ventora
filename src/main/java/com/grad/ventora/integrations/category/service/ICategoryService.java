package com.grad.ventora.integrations.category.service;
import com.grad.ventora.integrations.category.dto.*; import java.util.*;
public interface ICategoryService { List<CategoryResponse> getPublic(); List<CategoryResponse> getAdmin(); CategoryResponse create(CategoryRequest r); CategoryResponse update(Long id,CategoryRequest r); void delete(Long id); }
