## Automation
[am.list](main/java/am/list) - Page Objects \
[TestListAm](test/java/TestListAm) - tests
1. Task
   + Մտնում եք list.am
   + Վերևի աջ անկյունում լեզուն փոխում եք անգլերեն
   + Հետո hover եք անում Electronics-ի վրա ու ընտրում Notebooks-ը
   + Ստուգում եք, որ load էղած վերջին item-ը clickable-ա։ Ստուգումը կատարում եք թեստ մեթոդի մեջ testng-ի համապատասխան assertion-ով։
   > Ավելորդա ասելը, որ գրում եք Page Object Model-ի կիրառմամբ։ Որոշեցի POM ( Page Object Model )-ի վերաբերյալ լինքեր չտամ, որտև բացարձակ դժվարություն չկա նյութեր գտնելու ու գուգլելու մեջ։ Նենց որ բարի ժամանց :tropical_drink:
1. Task 
   + Մտնում եք list.am 
   + Ընտրում եք Real Estate -> Apartments
   + Ձախ կողմի վերևի ֆիլտրում ընտրում եք Agency։ 
   + Ամեն մի հայտնված item-ի նկարի ներքևի աջ անկյունում label կտենաք ավելացված, Agency վրեն գրված։ Պետքա համոզվեք, որ տվյալ page-ի բոլոր item-ներն ունեն էդ Agency label-ը։ Եթե հանկարծ կա բագ ու որևէ մի item ֆիլտրվելուց հետո չունի Agency label-ը, ուրեմն պետքա հենց էդ պահին ֆեյլով ծրագիրը ավարտվի ու նաև error message-ում գրվի տվյալ item-ի անունը, որի վրա որ չկա Agency label-ը։ Անուն ասելով նկատի ունեմ կցված նկարում առանձնացված տեքստը։
   > Ուշադրություն. Բնականաբար շատ քիչա հավանական, որ բագը լինի ու ամենայն հավանականությամբ հենց չկա, բայց դուք պետքա կարողանաք սիմուլացնեք բագը ու համոզվեք որ իրոք ճիշտ item-ի անունը տպվումա համապատասխան error message-ո. հետևաբար եթե իրական բագը լինի, ուրեմն Ձեր թեստը դա բռնելուա։ Որպես փոքր հուշում ասեմ, որ Դուք կարաք edit անեք html-ը։ Իհարկե պետքա հասկանաք երբ եք edit անում, թեստի աշխատանքի որ կետում և այլն, բայց դա Ձեր գործնա, Դուք էդ կարաք անեք համապատասխան աշխատասիրության ու կոֆեով մտածելու դեպքում :wink:Բարի ժամանց !
1. Task
   +  մտնում եք https://www.list.am/
   +  բացում notebooks բաժինը
   +  ընտրում currecy = AMD, location = Kentron
   +  ֆիլտրում գինը 200․000 - 500․000
   +  Ստուգում եք որ load եղած բոլոր item-ները ներկայացված են AMD-ով, գինը գտնվում ա Ձեր ընտրած գնի դիապազոնում ու բոլոր item-ները Ձեր ընտրած տարածաշրջանից են