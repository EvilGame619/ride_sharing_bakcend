package com.Uber.UberApplicaiton.config;

import com.Uber.UberApplicaiton.dto.PointDTO;
import com.Uber.UberApplicaiton.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(PointDTO.class, Point.class).setConverter(context -> {
            PointDTO pointDTO = context.getSource();
            return GeometryUtil.createPoint(pointDTO);
        });

        mapper.typeMap(Point.class, PointDTO.class).setConverter(context->{
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(),point.getY()
            };
            return new PointDTO(coordinates);
        });
        return mapper;
    }
}
