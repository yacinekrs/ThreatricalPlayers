public abstract class Play {

  public String name;


  public Play(String name) {
    this.name = name;
    // le type c'est les classes filles
  }
  public String getName(String name){
    return name;
  }

  public abstract int CalculeAmount(Performance perf);

  public int CalculeBonus(Performance perf){
    return 0;
  }

  
}

