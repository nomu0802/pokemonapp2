package com.example.pokemonapp2;

public class typewild {


    String typoe(String type){
        String typecolor = null;

        if(type.equals("ノーマル")){

            typecolor = "#BEBEBE";
        }


        else if(type.equals("ほのお")){

            typecolor = "#AF2432";
        }

        else if(type.equals("みず")){
            typecolor = "#48BDDB";



        }


        else if(type.equals("でんき")){

            typecolor = "#D3E201";



        }



        else if(type.equals("くさ")){


            typecolor = "#24AF57";


        }

        else if(type.equals("こおり")){


            typecolor = "#9BD1D5";


        }


        else if(type.equals("かくとう")){


            typecolor = "#F37841";


        }

        else if(type.equals("どく")){

            typecolor = "#7824AF";



        }

        else if(type.equals("じめん")){


            typecolor = "#BC5E1F";


        }

        else if(type.equals("ひこう")){


            typecolor = "#55C4EE";



        }

        else if(type.equals("エスパー")){


            typecolor = "#E931EF";


        }

        else if(type.equals("むし")){




            typecolor = "#56CB66";
        }

        else if(type.equals("いわ")){



            typecolor = "#9F902E";

        }

        else if(type.equals("ゴースト")){





            typecolor = "#6B4EE2";
        }

        else if(type.equals("ドラゴン")){

            typecolor = "#649BDB";



        }
        else if(type.equals("あく")){


            typecolor = "#4E4E4E";


        }
        else if(type.equals("はがね")){
            typecolor = "#787878";



        }
        else if(type.equals("フェアリー")){
            typecolor = "#EA99FE";




        }
        else {


            typecolor = "#00000000";

        }

        return typecolor;
    }



}
