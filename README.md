# Minimal Ecommerce

## Model

```java  
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Username;
    private String name;
}
```

## Repository

```java
@Repository
public interface UserRepository 
        extends JpaRepository<User, Long>, 
        PagingAndSortingRepository<User, Long> {}
```

## Service
### Service Interface
```java
public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long Id);
    User updateUser(Long Id, User updatedUser);
    void deleteUser(Long Id);
}
```
### Service Implementation
```java
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> getUserById(Long Id) {
        return userRepository.findById(Id);
    }
    @Override
    public User updateUser(Long Id, User updatedUser) {
        /*
         * Get the user by id using findById
         * since the findById returns a user
         * set the user returned to the updated user
         * then use the repository to save the user
         * if then the user is not found you must make an exception
         * */
        return userRepository.findById(Id).map(user-> {
            user.setName(updatedUser.getName());
            user.setUsername(updatedUser.getSurname());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User with this id is not found"));
    }
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}

```


## Controller 
```java

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Category> 
        getCategoryById(@PathVariable Long Id) {
            return categoryService.getCategoryById(Id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound()
                            .build());
        }

    @PutMapping("/{Id}")
    public ResponseEntity<Category> 
        updateCategory(@PathVariable Long Id, 
                       @RequestBody Category category){
            try{
                Category updatedCategory = categoryService
                        .updateCategory(Id, category);
                return ResponseEntity.ok(updatedCategory);
    
            } catch (RuntimeException ex){
                return ResponseEntity.notFound().build();
            }
        }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long Id){
        categoryService.deleteCategory(Id);
        return ResponseEntity.noContent().build();
    }
}
```
