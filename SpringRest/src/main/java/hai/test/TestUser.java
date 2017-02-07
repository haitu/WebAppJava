package hai.test;

import org.springframework.stereotype.Component;

@Component
public class TestUser {
	private String name;
    private int age;
    private String country;
    
    public TestUser()
    {
    	
    }
    public TestUser(String name, int age, String country)
    {
        this.name=name;
        this.age=age;
        this.country=country;
    }
    
    public String toString() {
    	return name + " is " + age + " years old, living in " + country;
    }
}
