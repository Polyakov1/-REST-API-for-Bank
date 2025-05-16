package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.mapper;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.UserResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.EmailData;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.PhoneData;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "emails", source = "emails", qualifiedByName = "mapEmails")
    @Mapping(target = "phones", source = "phones", qualifiedByName = "mapPhones")
    @Mapping(target = "balance", source = "account.balance")
    UserResponse toUserResponse(User user);

    @Named("mapEmails")
    default List<String> mapEmails(Set<EmailData> emails) {
        return emails.stream()
                .map(EmailData::getEmail)
                .collect(Collectors.toList());
    }

    @Named("mapPhones")
    default List<String> mapPhones(Set<PhoneData> phones) {
        return phones.stream()
                .map(PhoneData::getPhone)
                .collect(Collectors.toList());
    }
}