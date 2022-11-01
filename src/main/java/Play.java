public abstract class Play {

  public String name;


  public Play(String name) {
    this.name = name;
    // le type c'est les classes filles
  }
  public String getName(String name){
    return name;
  }

  public abstract int calculeAmount(int audience);

  public double calculeCredit(int audience){
    return Math.max(audience - 30, 0);
  }

  
}

