
package pt.ipp.estg.ed.doublelinkedlistimplementation;

/**
 *Classe que representa uma pessoa por nome e idade
 * @author SandroCunha8200785
 */
public class Person implements Comparable<Person>{
    
    private String name;
    private int age;
    

    public Person() {
        this.age = 0;
        this.name = "";
    }

    public Person(String pName, int pAge) {
        this.name = pName;
        this.age = pAge;
    }
    
    public Person(Person pPerson){
        
        this.name = pPerson.getName();
        this.age = pPerson.getAge();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder("Person ->");
        
        sb.append("\tName: "); sb.append(this.name);
        sb.append("\tAge: "); sb.append(this.age);
        
        return sb.toString();
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        
        return new Person(this);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        } 
        
        else if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        else {
            Person otherPerson = (Person) obj;
            
            return (this.name.equalsIgnoreCase(otherPerson.getName()) &&
                    this.age == otherPerson.getAge());
        }
    }


    @Override
    public int compareTo(Person pPerson) {
        
        // Comparação por nome (ordenado alfabeticamente)
        return this.name.compareTo(pPerson.getName());
    
    }
    
    
    
    
}
