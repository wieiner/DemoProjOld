package com.demo.demoproj.service.converter;

import com.demo.demoproj.repository.model.UserItem;
import com.demo.demoproj.service.model.UserItemDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserItemToUserItemDTOConverterImpl implements Converter<UserItem, UserItemDTO> {
    @Override
    public UserItemDTO convert(@NotNull UserItem from) {
        return new UserItemDTO(from.getId(), from.getUsername(), from.getPassword());
    }
}
