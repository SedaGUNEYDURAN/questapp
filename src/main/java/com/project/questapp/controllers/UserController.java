import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;	
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	@PostMaping
	public User createUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
}
