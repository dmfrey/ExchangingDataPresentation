insert into restaurant( name ) values ( 'Jamison Pubkick House' );

insert into menu( name, restaurant ) values ( 'Lunch Menu', 1 );
insert into menu( name, restaurant ) values ( 'Dinner Menu', 1 );

insert into section( name, menu ) values( 'Appetizers', 1 );
insert into section( name, menu ) values( 'Pub Fare', 1 );
insert into section( name, menu ) values( 'Salads', 1 );
insert into section( name, menu ) values( 'Deserts', 1 );
insert into section( name, menu ) values( 'Appetizers', 2 );
insert into section( name, menu ) values( 'Pub Fare', 2 );
insert into section( name, menu ) values( 'Salads', 2 );
insert into section( name, menu ) values( 'Entrees', 2 );
insert into section( name, menu ) values( 'Deserts', 2 );

insert into menu_item( name, description, price, section ) values( 'Soup of the Day', 'please ask your server', 4.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Pumpkin Seed Crusted Crab Cakes', 'Caribbean slaw, gin grapefruit aioli', 11.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Tempura Battered Sweet Onion Rings', 'Parmesan peppercorn dipping sauce', 7.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Vietnamese Lettuce Wraps', 'Bibb lettuce, marinated chicken, scallions, nuoc cham, Toasted jasmine rice powder', 8.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Cornmeal Crusted Fried Calamari', 'homemade tomato sauce & curry lemon aioli', 8.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Arugula Mascarpone & Bacon Dip', 'Grilled French baguette, applewood smoked bacon', 9.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Smoked Jail Island Salmon', 'Dill creme fraiche potato salad, red onion, pickle-caper relish', 10.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Prince Edward Island Mussels', 'Capers, shaved fennel, arugula pesto, plum tomatoes, parmesan cheese, White wine garlic butter', 10.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Crispy Apple & Sweet Potato Wedges', 'Organic maple yogurt, spicy miso ketchup', 8.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Sweet Chile-Soy Laughing Bird Shrimp', 'Fried rice noodles, edamame green pea puree, sriracha oil', 9.00, 1 );
insert into menu_item( name, description, price, section ) values( 'Seared Sea Scallops', 'Fried parmesan polenta, tomato basil ragout, pancetta', 10.00, 1 );

insert into menu_item( name, description, price, section ) values( 'Classic Black Angus Burger', 'lettuce, tomato & raw onion, choice of cheese: american, cheddar, gruyere, bleu cheese', 8.50, 2 );
insert into menu_item( name, description, price, section ) values( 'Hawaiian Burger', 'Caramelized pineapple, sriracha aioli, brioche, friendly farm pepper-jack cheese', 9.50, 2 );
insert into menu_item( name, description, price, section ) values( 'Bistro Burger', 'red onion marmalade, crumbled bleu cheese, chipotle mayo, ciabatta roll', 9.50, 2 );
insert into menu_item( name, description, price, section ) values( 'Bbq Blackened Chicken Sandwich', 'Chiffonade romaine, apple wood bacon Friendly farm cheddar, coca-cola bbq sauce', 8.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Turkey Club Sandwich', 'house roasted turkey breast, bacon, lettuce, tomato, smoky mayo, golden wheat bread', 8.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Grilled Vegetable and Tofu Wrap', 'grilled peppers, onions, portabella and tofu, fresh mozzarella, spinach and pesto mayo', 8.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Chicken or Eggplant Parmesan Sandwich', 'fresh mozzarella, ciabatta roll, homemade tomato sauce', 8.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Grilled Pastrami on Jewish Rye', 'spicy dijon mustard, fresh pickles, caramelized onions, gruyere cheese', 8.50, 2 );
insert into menu_item( name, description, price, section ) values( 'Open Faced Turkey Sandwich', 'homemade gravy, dried cranberry chutney, brioche', 9.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Roasted Chicken Salad Wrap', 'spring mix lettuce, tarragon mayonnaise, tomato, sliced apples', 7.50, 2 );
insert into menu_item( name, description, price, section ) values( 'Pizza Cheese Steak', 'sliced ribeye steak, tomato sauce, provolone, caramelized onions, fresh steak roll', 9.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Jumbo Fried Chicken Wings', 'choice of: mild, coca-cola bbq, honey garlic, kung pao', 8.00, 2 );
insert into menu_item( name, description, price, section ) values( 'Individual Lobster Pot Pie', 'chunks of lobster, tarragon béchamel sauce, peas, carrots, diced potatoes, puff pastry top', 18.00, 2 );

insert into menu_item( name, description, price, section ) values( 'Grilled Steak Salad', 'blackened or grilled, spring mix greens and romaine, crumbled bleu cheese, red onion, mushrooms, grape tomatoes, choice of dressing', 13.00, 3 );
insert into menu_item( name, description, price, section ) values( 'Buffalo Shrimp Salad', 'grilled baby white shrimp tossed with our honey garlic hot sauce, spring mix greens, cucumber, tomato, carrots, sweet onion vinaigrette', 11.00, 3 );
insert into menu_item( name, description, price, section ) values( 'Roasted Chicken Salad Nicoise', 'spring mix greens, homemade roasted chicken salad, green beans, carrots, grape tomatoes, nicoise olives, fried hard boiled egg. Add Imported Italian Anchovies  1.50', 10.00, 3 );
insert into menu_item( name, description, price, section ) values( 'Mixed Greens Salad', 'spring mix baby greens, cucumber, tomato, carrots', 6.50, 3 );
insert into menu_item( name, description, price, section ) values( 'Baby Spinach Salad', 'baby spinach, red onion, sweet and spicy cashews, dried cherries and cranberries tossed with sweet mango-chile vinaigrette', 7.50, 3 );
insert into menu_item( name, description, price, section ) values( 'Caesar Salad', 'crisp hearts of romaine, croutons, parmesan cheese and our homemade heart healthy caesar dressing. Add Imported Italian Anchovies  1.50', 7.50, 3 );

insert into menu_item( name, description, price, section ) values( 'Vanilla Creme Brulee', 'farm fresh eggs, fresh berry garnish', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Brown Sugar Banana Brioche Bread Pudding', 'Vanilla bean ice cream, hot buttered rum sauce', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Vanilla New York Cheesecake', 'mixed berry compote', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Warm Chocolate & Peanut Butter Indulgence', 'Belgium chocolate ganache', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Moist Carrot Cake', 'pecans, coconut, raisins, orange zest, crystallized ginger, cream cheese frosting', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Local Vanilla Bean Ice Cream', 'warm chocolate ganache', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Vanilla Panna Cotta', 'Diced strawberries, cranberry syrup', 6.50, 4 );
insert into menu_item( name, description, price, section ) values( 'Butterscotch-Vanilla Ice Cream', 'Toffee chips', 6.50, 4 );

insert into menu_item( name, description, price, section ) values( 'Soup of the Day', 'please ask your server', 4.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Pumpkin Seed Crusted Crab Cakes', 'Caribbean slaw, gin grapefruit aioli', 11.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Tempura Battered Sweet Onion Rings', 'Parmesan peppercorn dipping sauce', 7.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Vietnamese Lettuce Wraps', 'Bibb lettuce, marinated chicken, scallions, nuoc cham, Toasted jasmine rice powder', 8.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Cornmeal Crusted Fried Calamari', 'homemade tomato sauce & curry lemon aioli', 8.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Arugula Mascarpone & Bacon Dip', 'Grilled French baguette, applewood smoked bacon', 9.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Smoked Jail Island Salmon', 'Dill creme fraiche potato salad, red onion, pickle-caper relish', 10.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Prince Edward Island Mussels', 'Capers, shaved fennel, arugula pesto, plum tomatoes, parmesan cheese, White wine garlic butter', 10.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Crispy Apple & Sweet Potato Wedges', 'Organic maple yogurt, spicy miso ketchup', 8.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Sweet Chile-Soy Laughing Bird Shrimp', 'Fried rice noodles, edamame green pea puree, sriracha oil', 9.00, 5 );
insert into menu_item( name, description, price, section ) values( 'Seared Sea Scallops', 'Fried parmesan polenta, tomato basil ragout, pancetta', 10.00, 5 );

insert into menu_item( name, description, price, section ) values( 'Classic Black Angus Burger', 'lettuce, tomato & raw onion, choice of cheese: american, cheddar, gruyere, bleu cheese', 8.50, 6 );
insert into menu_item( name, description, price, section ) values( 'Hawaiian Burger', 'Caramelized pineapple, sriracha aioli, brioche, friendly farm pepper-jack cheese', 9.50, 6 );
insert into menu_item( name, description, price, section ) values( 'Bistro Burger', 'red onion marmalade, crumbled bleu cheese, chipotle mayo, ciabatta roll', 9.50, 6 );
insert into menu_item( name, description, price, section ) values( 'Bbq Blackened Chicken Sandwich', 'Chiffonade romaine, apple wood bacon Friendly farm cheddar, coca-cola bbq sauce', 8.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Turkey Club Sandwich', 'house roasted turkey breast, bacon, lettuce, tomato, smoky mayo, golden wheat bread', 8.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Grilled Vegetable and Tofu Wrap', 'grilled peppers, onions, portabella and tofu, fresh mozzarella, spinach and pesto mayo', 8.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Chicken or Eggplant Parmesan Sandwich', 'fresh mozzarella, ciabatta roll, homemade tomato sauce', 8.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Grilled Pastrami on Jewish Rye', 'spicy dijon mustard, fresh pickles, caramelized onions, gruyere cheese', 8.50, 6 );
insert into menu_item( name, description, price, section ) values( 'Open Faced Turkey Sandwich', 'homemade gravy, dried cranberry chutney, brioche', 9.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Roasted Chicken Salad Wrap', 'spring mix lettuce, tarragon mayonnaise, tomato, sliced apples', 7.50, 6 );
insert into menu_item( name, description, price, section ) values( 'Pizza Cheese Steak', 'sliced ribeye steak, tomato sauce, provolone, caramelized onions, fresh steak roll', 9.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Jumbo Fried Chicken Wings', 'choice of: mild, coca-cola bbq, honey garlic, kung pao', 8.00, 6 );
insert into menu_item( name, description, price, section ) values( 'Individual Lobster Pot Pie', 'chunks of lobster, tarragon béchamel sauce, peas, carrots, diced potatoes, puff pastry top', 18.00, 6 );

insert into menu_item( name, description, price, section ) values( 'Grilled Steak Salad', 'blackened or grilled, spring mix greens and romaine, crumbled bleu cheese, red onion, mushrooms, grape tomatoes, choice of dressing', 13.00, 7 );
insert into menu_item( name, description, price, section ) values( 'Buffalo Shrimp Salad', 'grilled baby white shrimp tossed with our honey garlic hot sauce, spring mix greens, cucumber, tomato, carrots, sweet onion vinaigrette', 11.00, 7 );
insert into menu_item( name, description, price, section ) values( 'Roasted Chicken Salad Nicoise', 'spring mix greens, homemade roasted chicken salad, green beans, carrots, grape tomatoes, nicoise olives, fried hard boiled egg. Add Imported Italian Anchovies  1.50', 10.00, 7 );
insert into menu_item( name, description, price, section ) values( 'Mixed Greens Salad', 'spring mix baby greens, cucumber, tomato, carrots', 6.50, 7 );
insert into menu_item( name, description, price, section ) values( 'Baby Spinach Salad', 'baby spinach, red onion, sweet and spicy cashews, dried cherries and cranberries tossed with sweet mango-chile vinaigrette', 7.50, 7 );
insert into menu_item( name, description, price, section ) values( 'Caesar Salad', 'crisp hearts of romaine, croutons, parmesan cheese and our homemade heart healthy caesar dressing. Add Imported Italian Anchovies  1.50', 7.50, 7 );

insert into menu_item( name, description, price, section ) values( 'Grilled Rib eye Steak', 'Pennsylvania noble cheddar & bacon mashed potatoes, Homemade peppercorn steak sauce', 27.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Five Spiced New Zealand Lamb Rack', 'roasted redskin potatoes, red wine demi-glace, jalapeno mint jelly', 28.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Pan Seared Veal Marsala', 'fresh veal loin, organic Pennsylvania mushrooms, chive mashed potatoes, Black truffle essence', 24.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Prime Reserve Pork Mignon', 'Butternut squash spaetzle, fennel, spinach, chipotle molasses barbeque', 20.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Grilled Filet Mignon', 'tempura onion rings, mascarpone mashed potatoes, fresh broccoli', 30.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Pan Seared Chicken Chesapeake', 'Grilled corn mashed potatoes, jumbo lump crab, asparagus, Old bay pan jus', 21.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Eggplant, Chicken or Veal Parmesan', 'angel hair or penne pasta, homemade tomato sauce and garlic bread', 16.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Creamy Chicken or Shrimp Pesto Pasta ', 'homemade pesto, penne pasta, sundried tomatoes, asparagus', 19.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Pecan Crusted Jumbo Rainbow Trout', 'Brown butter spaghetti squash, lentil & pearl couscous salad, warm apple wood bacon vinaigrette', 20.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Grilled Jail Island Salmon', 'Maple roasted brussels sprouts, sweet potato latkeswarm granny smith foam', 22.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Pan Seared Sea Scallops', 'Prosciutto wrapped asparagus, roasted garlic white bean puree, Meyer lemon gremolata', 23.00, 8 );
insert into menu_item( name, description, price, section ) values( 'Grilled Vegetable and Tofu Tower ', 'Portabellas, zucchini, red peppers, sweet onions, pearl cous-cous', 18.00, 8 );

insert into menu_item( name, description, price, section ) values( 'Vanilla Creme Brulee', 'farm fresh eggs, fresh berry garnish', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Brown Sugar Banana Brioche Bread Pudding', 'Vanilla bean ice cream, hot buttered rum sauce', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Vanilla New York Cheesecake', 'mixed berry compote', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Warm Chocolate & Peanut Butter Indulgence', 'Belgium chocolate ganache', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Moist Carrot Cake', 'pecans, coconut, raisins, orange zest, crystallized ginger, cream cheese frosting', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Local Vanilla Bean Ice Cream', 'warm chocolate ganache', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Vanilla Panna Cotta', 'Diced strawberries, cranberry syrup', 6.50, 9 );
insert into menu_item( name, description, price, section ) values( 'Butterscotch-Vanilla Ice Cream', 'Toffee chips', 6.50, 9 );
