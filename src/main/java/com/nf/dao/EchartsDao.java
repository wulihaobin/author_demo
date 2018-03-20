package com.nf.dao;

import com.nf.entity.Echarts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchartsDao extends JpaRepository<Echarts, Long> {
}