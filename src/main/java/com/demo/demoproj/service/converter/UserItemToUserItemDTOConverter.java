package com.demo.demoproj.service.converter;

import com.demo.demoproj.repository.model.UserItem;
import com.demo.demoproj.service.model.UserItemDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserItemToUserItemDTOConverter implements Converter<UserItemDTO, UserItem> {
    @Override
    public UserItem convert(@NotNull UserItemDTO from) {
        return new UserItem(from.getId(), from.getUsername(), from.getPassword());
    }
}
