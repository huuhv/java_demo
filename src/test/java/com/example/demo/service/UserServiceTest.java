package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Test for UserService
 * Unit Test cho UserService
 *
 * Uses @ExtendWith(MockitoExtension.class): runs with Mockito, no Spring context needed.
 * Su dung @ExtendWith(MockitoExtension.class): chay voi Mockito, khong can khoi dong Spring.
 *
 * @Mock creates a fake UserRepository (no real DB calls).
 * @Mock tao UserRepository gia (khong goi DB that).
 *
 * @InjectMocks creates a real UserService with mock dependencies injected.
 * @InjectMocks tao UserService that va inject mock dependency vao trong.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    /**
     * Runs before EACH test to reset sample data.
     * Chay truoc MOI test de reset du lieu mau.
     */
    @BeforeEach
    void setUp() {
        user1 = new User("Nguyen Van A", "nguyenvana@example.com", "0901234567", "Ha Noi");
        user1.setId(1L);

        user2 = new User("Tran Thi B", "tranthib@example.com", "0912345678", "Ho Chi Minh");
        user2.setId(2L);
    }

    // ==================== CREATE ====================

    /**
     * createUser: should save and return the new user.
     * createUser: phai luu va tra ve user moi.
     */
    @Test
    void createUser_shouldSaveAndReturnUser() {
        // Arrange: mock repository.save() to return user1
        when(userRepository.save(any(User.class))).thenReturn(user1);

        // Act
        User result = userService.createUser(user1);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Nguyen Van A");
        assertThat(result.getEmail()).isEqualTo("nguyenvana@example.com");

        verify(userRepository, times(1)).save(user1);
    }

    // ==================== READ ====================

    /**
     * getAllUsers: should return list of all users.
     * getAllUsers: phai tra ve danh sach tat ca users.
     */
    @Test
    void getAllUsers_shouldReturnList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.getAllUsers();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result).extracting(User::getName)
                .containsExactly("Nguyen Van A", "Tran Thi B");

        verify(userRepository, times(1)).findAll();
    }

    /**
     * getAllUsers: should return empty list when no users exist.
     * getAllUsers: phai tra ve danh sach rong khi chua co user nao.
     */
    @Test
    void getAllUsers_shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.getAllUsers();

        assertThat(result).isNotNull().isEmpty();
        verify(userRepository, times(1)).findAll();
    }

    /**
     * getUserById: should return user when ID exists.
     * getUserById: phai tra ve user khi ID ton tai.
     */
    @Test
    void getUserById_found_shouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        Optional<User> result = userService.getUserById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        assertThat(result.get().getName()).isEqualTo("Nguyen Van A");

        verify(userRepository, times(1)).findById(1L);
    }

    /**
     * getUserById: should return empty Optional when ID does not exist.
     * getUserById: phai tra ve Optional rong khi ID khong ton tai.
     */
    @Test
    void getUserById_notFound_shouldReturnEmpty() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(99L);

        assertThat(result).isEmpty();
        verify(userRepository, times(1)).findById(99L);
    }

    /**
     * getUserByEmail: should return user when email exists.
     * getUserByEmail: phai tra ve user khi email ton tai.
     */
    @Test
    void getUserByEmail_found_shouldReturnUser() {
        when(userRepository.findByEmail("nguyenvana@example.com"))
                .thenReturn(Optional.of(user1));

        Optional<User> result = userService.getUserByEmail("nguyenvana@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("nguyenvana@example.com");

        verify(userRepository, times(1)).findByEmail("nguyenvana@example.com");
    }

    /**
     * getUserByEmail: should return empty Optional when email does not exist.
     * getUserByEmail: phai tra ve Optional rong khi email khong ton tai.
     */
    @Test
    void getUserByEmail_notFound_shouldReturnEmpty() {
        when(userRepository.findByEmail("notfound@example.com"))
                .thenReturn(Optional.empty());

        Optional<User> result = userService.getUserByEmail("notfound@example.com");

        assertThat(result).isEmpty();
        verify(userRepository, times(1)).findByEmail("notfound@example.com");
    }

    // ==================== UPDATE ====================

    /**
     * updateUser: should update and return user when ID exists.
     * updateUser: phai cap nhat va tra ve user khi ID ton tai.
     */
    @Test
    void updateUser_found_shouldUpdateAllFields() {
        User updatedDetails = new User(
                "Nguyen Van A Updated", "new_email@example.com", "0999999999", "Da Nang");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        // thenAnswer returns the argument passed to save() - simulates DB saving
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.updateUser(1L, updatedDetails);

        assertThat(result.getName()).isEqualTo("Nguyen Van A Updated");
        assertThat(result.getEmail()).isEqualTo("new_email@example.com");
        assertThat(result.getPhone()).isEqualTo("0999999999");
        assertThat(result.getAddress()).isEqualTo("Da Nang");

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user1);
    }

    /**
     * updateUser: should throw RuntimeException when ID does not exist.
     * updateUser: phai nem RuntimeException khi ID khong ton tai.
     */
    @Test
    void updateUser_notFound_shouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User updatedDetails = new User("Ghost", "ghost@example.com", null, null);

        assertThatThrownBy(() -> userService.updateUser(99L, updatedDetails))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");

        // save() must NOT be called when user is not found
        verify(userRepository, never()).save(any(User.class));
    }

    // ==================== DELETE ====================

    /**
     * deleteUser: should delete user when ID exists.
     * deleteUser: phai xoa user khi ID ton tai.
     */
    @Test
    void deleteUser_found_shouldDeleteSuccessfully() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        doNothing().when(userRepository).delete(user1);

        assertThatCode(() -> userService.deleteUser(1L)).doesNotThrowAnyException();

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user1);
    }

    /**
     * deleteUser: should throw RuntimeException when ID does not exist.
     * deleteUser: phai nem RuntimeException khi ID khong ton tai.
     */
    @Test
    void deleteUser_notFound_shouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");

        // delete() must NOT be called when user is not found
        verify(userRepository, never()).delete(any(User.class));
    }
}
