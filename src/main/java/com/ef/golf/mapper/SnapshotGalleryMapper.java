package com.ef.golf.mapper;

import com.ef.golf.pojo.SnapshotGallery;

public interface SnapshotGalleryMapper {
    int insert(SnapshotGallery record);

    int insertSelective(SnapshotGallery record);
}