package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.TransferRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferRequest request, String currentUserEmail);
}