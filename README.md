
# Rapport

**Assignemtn 5 - Networking**

Uppgiften gick ut på att skapa en applikation som hämtar JSON-data och göra om detta till objekt i Java och skriva ut informationen. För att göra detta krävs det att appen kan hämta data från nätet. Vilket sker genom att man ger den permission till internet. URL:en man fick är en array därför krävs det göra en parse av arrayen med hjälp av Java. 
```Java
try {
        // Ditt JSON-objekt som Java
        JSONArray jsonarray = new JSONArray(json);
```
Strängen som hämtas från URL:en består av en array. Därför måste arrayen göras till ett JSON objekt för att hämta ut datan. Därför måste arrayen itereras med en for-loop t ex. För att lagra värderna i variabler eller objekt i detta fallet av klassen Mountain.

```Java
  for(int i = 0; i < jsonarray.length(); i++){
    JSONObject jsonObject = jsonarray.getJSONObject(i);
    String name = jsonObject.getString("name");
    int height = jsonObject.getInt("size");
    String location = jsonObject.getString("location");
    mountains.add(new Mountain(name, height, location));
    adapter.notifyDataSetChanged();
    }
```
Här skapas nya objekt av Mountain med namn, höjd och var berget är.

Alla berg läggs i en arrayList som är kopplad till en adapterArray för att kunna rita ut de i en ListView. När all data hämtats och lagts i olika objekt så körs en uppdatering så alla berg ritas ut i ListView.

Så här ser det ut:

BILD

Klickar man på något av bergen i listan kommer man till en ny activity som visar det berget man klickat på med lite mer information. Även en knapp finns så man kan återgå till förra activity sidan.

```Java
  String name = extras.getString("name");
  int height = extras.getInt("height");
  String location = extras.getString("location");
  // Do something with the name and number
  Log.d("a19jimsa", name+height+location);
  TextView nameView = findViewById(R.id.textView_name);
  nameView.setText("Name: " + name);
  TextView heightView = findViewById(R.id.textView_height);
  heightView.setText("Height: "+height);
  TextView locationView = findViewById(R.id.textView_location);
  locationView.setText("Location: "+location);
  }
  Button button = findViewById(R.id.back_button);
 ```
 Här skickas värden över beroende på det berg man klickat på. Och sätts till respektive TextView och skriver ut information om berget.
 
 ```Java
 Mountain mountain = adapter.getItem(position);
 Intent intent = new Intent(MainActivity.this, SecondActivity.class);
 intent.putExtra("name", mountain.getName());
 intent.putExtra("height", mountain.getHeight());
 intent.putExtra("location", mountain.getLocation());
 startActivity(intent);
```
adapte.getItem är det objektet man klickat på i listan som befinner sig på den positionen och mountain är en referens av det objektet och hämtar utt dess information med egenskapade getter funktioner. Som sedan skickas till secondActivity.

Det ser ut så här:

BILD

