package uz.everbest.buxorossb.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.everbest.buxorossb.dto.AlertResponseDto;
import uz.everbest.buxorossb.dto.JWTTokenDto;
import uz.everbest.buxorossb.dto.user.CreationUserDto;
import uz.everbest.buxorossb.dto.user.UserDto;
import uz.everbest.buxorossb.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUser(Pageable pageable) {
        Page<UserDto> result = userService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<JWTTokenDto> createUser(@RequestBody @Valid CreationUserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.save(userDto));
    }

//    @PutMapping
//    public ResponseEntity<JWTTokenDto> updateUser(@RequestBody @Valid UserDto userDto) throws Exception {
//        if(userDto.getId() == null) {
//            throw new BadRequestAlertException("Id is coming null", "User", "adminId");
//        }
//        return ResponseEntity.ok(userService.save(userDto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlertResponseDto> deleteUser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.delete(id));
    }

}
