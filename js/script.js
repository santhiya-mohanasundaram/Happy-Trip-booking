/**
 * TRIPEASE - PREMIUM TRAVEL BOOKING SYSTEM
 * Master JavaScript Application
 * Vanilla JS only (No external frontend frameworks)
 */

// --- BACKEND API BASE URL ---
const API_BASE_URL = "http://localhost:8080/api";

// --- GLOBAL SAMPLE DATA ---
const TRIPEASE_DATA = {
  destinations: [
    {
      id: "kashmir",
      name: "Kashmir",
      country: "India",
      region: "North India",
      tag: "Heaven on Earth",
      vibe: "Snow & Mountains",
      price: 24999,
      toursCount: 14,
      image: "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=800&q=80",
      description: "Pristine valleys, serene Shikara rides on Dal Lake, and snow-kissed Himalayan slopes in Gulmarg & Pahalgam.",
      bestSeason: "March to October",
      avgTemp: "14°C - 22°C"
    },
    {
      id: "kerala",
      name: "Kerala",
      country: "India",
      region: "South India",
      tag: "God's Own Country",
      vibe: "Tropical & Backwaters",
      price: 19999,
      toursCount: 18,
      image: "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=800&q=80",
      description: "Tranquil backwater houseboats in Alleppey, emerald tea plantations in Munnar, and Ayurvedic wellness retreats.",
      bestSeason: "September to March",
      avgTemp: "23°C - 30°C"
    },
    {
      id: "goa",
      name: "Goa",
      country: "India",
      region: "West India",
      tag: "Sun, Sand & Sea",
      vibe: "Beaches",
      price: 14499,
      toursCount: 22,
      image: "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=800&q=80",
      description: "Golden palm-fringed beaches, Portuguese heritage churches, vibrant sunset shacks, and exhilarating water sports.",
      bestSeason: "October to April",
      avgTemp: "25°C - 32°C"
    },
    {
      id: "ladakh",
      name: "Ladakh",
      country: "India",
      region: "North India",
      tag: "Land of High Passes",
      vibe: "Snow & Mountains",
      price: 32999,
      toursCount: 9,
      image: "https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=800&q=80",
      description: "Mesmerizing blue waters of Pangong Tso, high mountain passes of Khardung La, and ancient Buddhist monasteries.",
      bestSeason: "May to September",
      avgTemp: "8°C - 18°C"
    },
    {
      id: "rajasthan",
      name: "Rajasthan",
      country: "India",
      region: "North India",
      tag: "Royal Heritage",
      vibe: "Heritage & Palaces",
      price: 26499,
      toursCount: 16,
      image: "https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=800&q=80",
      description: "Opulent palaces of Udaipur, towering golden forts of Jaisalmer, pink architecture of Jaipur, and desert camel safaris.",
      bestSeason: "October to March",
      avgTemp: "15°C - 28°C"
    },
    {
      id: "himachal",
      name: "Himachal Pradesh",
      country: "India",
      region: "North India",
      tag: "Mountain Paradise",
      vibe: "Snow & Mountains",
      price: 16999,
      toursCount: 20,
      image: "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?auto=format&fit=crop&w=800&q=80",
      description: "Snowy adventures in Solang Valley, picturesque pines of Manali, colonial charm of Shimla, and cozy mountain cafes.",
      bestSeason: "All Year Round",
      avgTemp: "10°C - 20°C"
    },
    {
      id: "andaman",
      name: "Andaman Islands",
      country: "India",
      region: "Islands",
      tag: "Exotic Coral Reefs",
      vibe: "Beaches",
      price: 31999,
      toursCount: 11,
      image: "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?auto=format&fit=crop&w=800&q=80",
      description: "Radhanagar's pristine white sands, crystal-clear scuba diving waters, bioluminescence, and lush mangrove trails.",
      bestSeason: "October to May",
      avgTemp: "24°C - 30°C"
    },
    {
      id: "bali",
      name: "Bali",
      country: "Indonesia",
      region: "International",
      tag: "Island of the Gods",
      vibe: "Tropical & Backwaters",
      price: 45999,
      toursCount: 15,
      image: "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=800&q=80",
      description: "Iconic Tegallalang rice terraces, sacred sea temples in Tanah Lot, beach clubs in Seminyak, and private pool villas.",
      bestSeason: "April to October",
      avgTemp: "26°C - 31°C"
    },
    {
      id: "dubai",
      name: "Dubai",
      country: "United Arab Emirates",
      region: "International",
      tag: "Futuristic Wonder",
      vibe: "Heritage & Palaces",
      price: 49999,
      toursCount: 12,
      image: "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&w=800&q=80",
      description: "Burj Khalifa sky deck, exhilarating 4x4 desert safaris with BBQ dinners, luxury marina cruises, and mega mall shopping.",
      bestSeason: "November to March",
      avgTemp: "20°C - 29°C"
    },
    {
      id: "maldives",
      name: "Maldives",
      country: "Maldives",
      region: "International",
      tag: "Ultra Luxury Escape",
      vibe: "Beaches",
      price: 89999,
      toursCount: 8,
      image: "https://images.unsplash.com/photo-1514282401047-d79a71a590e8?auto=format&fit=crop&w=800&q=80",
      description: "Iconic overwater bungalows over turquoise lagoons, romantic private beach dinners, manta ray snorkeling, and premier spa treatments.",
      bestSeason: "November to April",
      avgTemp: "27°C - 31°C"
    },
    {
      id: "switzerland",
      name: "Swiss Alps",
      country: "Switzerland",
      region: "International",
      tag: "Alpine Romance",
      vibe: "Snow & Mountains",
      price: 129999,
      toursCount: 6,
      image: "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?auto=format&fit=crop&w=800&q=80",
      description: "Snow-capped peaks of Jungfraujoch, crystal lakes of Lucerne, scenic Glacier Express trains, and traditional chocolate tasting.",
      bestSeason: "May to October & Dec-Feb",
      avgTemp: "12°C - 22°C"
    }
  ],

  packages: [
    {
      id: "pkg-1",
      title: "Mesmerizing Kashmir & Dal Lake Paradise",
      destination: "Kashmir, India",
      region: "North India",
      category: "Honeymoon",
      duration: "5 Nights / 6 Days",
      daysCount: 6,
      nightsCount: 5,
      price: 24999,
      originalPrice: 34999,
      rating: 4.9,
      reviewsCount: 148,
      badge: "Bestseller",
      featured: true,
      image: "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1566837945700-30057527ade0?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Experience the timeless enchantment of Kashmir, celebrated as paradise on earth. Stay in a luxurious carved wooden houseboat on the serene Dal Lake, glide through floating flower markets on a private Shikara, ride the world-famous Gulmarg Gondola to snow peaks, and wander along the gushing Lidder river in Pahalgam.",
      highlights: [
        "Traditional Deluxe Houseboat stay on Dal Lake with evening Shikara ride",
        "Gondola Cable Car Ride in Gulmarg up to Phase 1 snow point",
        "Picturesque drive through saffron fields and Apple Orchards in Pahalgam",
        "Visits to Mughal Gardens: Shalimar Bagh, Nishat Bagh & Chashme Shahi",
        "Authentic Kashmiri Wazwan dinner experience and Kahwa tea tasting"
      ],
      inclusions: [
        "5 Nights Deluxe 4-Star Accommodation (4N Hotel + 1N Houseboat)",
        "Daily Morning Buffet Breakfast & Chef-curated Dinners",
        "Private AC Sedan for all transfers, sightseeing, and airport pick-and-drop",
        "1-Hour Complementary Sunset Shikara Ride on Dal Lake",
        "All toll taxes, parking fees, driver allowance & inner line permits",
        "24/7 Dedicated Trip Concierge & Local Guide Assistance"
      ],
      exclusions: [
        "Airfare / Train tickets to and from Srinagar",
        "Gondola Phase 2 tickets (can be added on request)",
        "Pony rides, sledging, or snow scooter activities in Gulmarg/Pahalgam",
        "Personal expenses, laundry, telephone calls, and camera charges",
        "Travel Insurance (available as optional add-on during booking)"
      ],
      stays: [
        { name: "The Grand Mamta / Radisson Srinagar", rating: "4.8★ Luxury" },
        { name: "Royal Heritage Houseboat, Dal Lake", rating: "4.9★ Heritage" },
        { name: "Kolahoi Green Resort, Pahalgam", rating: "4.7★ Scenic Resort" }
      ],
      itinerary: [
        {
          day: 1,
          title: "Arrival in Srinagar & Sunset Shikara Ride on Dal Lake",
          description: "Arrive at Srinagar Airport where our representative will welcome you with traditional Kashmiri hospitality. Transfer to your luxury houseboat on Dal Lake. In the evening, enjoy a 1-hour relaxing Shikara boat ride watching the golden sunset over the lake and visit the floating market. Overnight stay in Houseboat."
        },
        {
          day: 2,
          title: "Srinagar to Gulmarg Excursion - The Meadow of Flowers",
          description: "After breakfast, embark on a scenic drive to Gulmarg (56 km / 2 hours). Witness breathtaking views of snow-covered Pir Panjal mountain ranges. Take the thrilling Gondola cable car ride to Kongdoori (Phase 1). Enjoy snow activities or golfing at the highest golf course. Return to Srinagar hotel for dinner."
        },
        {
          day: 3,
          title: "Srinagar to Pahalgam - Valley of Shepherds",
          description: "Proceed to Pahalgam via Pampore's saffron fields and the historic ruins of Avantipur. Marvel at the Lidder River flowing alongside pine forests. Explore the scenic Betaab Valley, Aru Valley, and Chandanwari. Check into your riverside resort in Pahalgam."
        },
        {
          day: 4,
          title: "Pahalgam Leisure & Srinagar Mughal Gardens",
          description: "Spend a relaxing morning by the Lidder River soaking in nature. Later drive back to Srinagar and tour the famed Mughal Gardens: Shalimar Bagh (Abode of Love) and Nishat Bagh (Garden of Pleasure). Explore local handicraft markets for Pashmina shawls and walnuts."
        },
        {
          day: 5,
          title: "Day Excursion to Sonmarg - Meadow of Gold",
          description: "Drive along the Sindh River valley to Sonmarg. Admire the colossal Thajiwas Glacier and rugged Himalayan peaks. Ideal for nature walks and pony rides across alpine streams. Return to Srinagar for a celebratory farewell Kashmiri dinner."
        },
        {
          day: 6,
          title: "Farewell Srinagar & Airport Departure",
          description: "Savor a warm breakfast with fresh bakery breads and Kashmiri Kahwa. Our chauffeur will escort you to Srinagar Airport with sweet memories of paradise."
        }
      ]
    },
    {
      id: "pkg-2",
      title: "Enchanting Kerala Backwaters & Munnar Hills",
      destination: "Kerala, India",
      region: "South India",
      category: "Family",
      duration: "4 Nights / 5 Days",
      daysCount: 5,
      nightsCount: 4,
      price: 19999,
      originalPrice: 27999,
      rating: 4.8,
      reviewsCount: 210,
      badge: "Trending",
      featured: true,
      image: "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1593693397690-362cb9666fc2?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1544644181-1484b3fdfc62?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Unwind in the blissful embrace of God's Own Country. Traverse mist-cloaked tea estates in Munnar, witness endangered Nilgiri Tahr at Eravikulam, spice plantations in Thekkady, and spend a night on an authentic thatched houseboat cruising through the calm backwaters of Alleppey.",
      highlights: [
        "Private AC Deluxe Houseboat overnight cruise in Alleppey backwaters",
        "Tea tasting and plantation tour in Munnar's rolling hills",
        "Visit Cheeyappara & Valara cascading waterfalls",
        "Periyar Wildlife Sanctuary boat safari in Thekkady",
        "Traditional Kerala Sadhya lunch served on banana leaf"
      ],
      inclusions: [
        "4 Nights Handpicked 4-Star Accommodations (2N Munnar, 1N Thekkady, 1N Houseboat)",
        "Daily Breakfast at all hotels + All Meals on Houseboat (Lunch, Tea, Dinner, Breakfast)",
        "Private AC Vehicle with professional chauffeur for the entire trip",
        "Guided Spice Plantation tour & Tea Factory visit",
        "All toll, parking, driver bata and statutory government taxes"
      ],
      exclusions: [
        "Flight/Train tickets to Cochin (COK)",
        "Entry fees at monuments, national parks & cultural dance shows",
        "Ayurvedic massage treatments (can be booked at resort)",
        "Anything not mentioned in inclusions"
      ],
      stays: [
        { name: "Amber Dale Luxury Resort, Munnar", rating: "4.8★ Hill Resort" },
        { name: "Greenwoods Resort, Thekkady", rating: "4.7★ Nature Stay" },
        { name: "Rainbow Cruises Houseboat, Alleppey", rating: "4.9★ Backwater Haven" }
      ],
      itinerary: [
        {
          day: 1,
          title: "Cochin Arrival to Munnar Hill Station",
          description: "Pickup from Cochin Airport / Railway Station. Enjoy scenic drive to Munnar passing Cheeyappara waterfalls, cardamom hills and rubber plantations. Check in at resort."
        },
        {
          day: 2,
          title: "Full Day Munnar Tea Country Exploration",
          description: "Visit Eravikulam National Park, Mattupetty Dam, Echo Point and Tata Tea Museum. Sample exotic high-altitude teas."
        },
        {
          day: 3,
          title: "Munnar to Thekkady Spice Wonderland",
          description: "Scenic drive to Periyar. Explore fragrant spice plantations of cardamom, pepper, vanilla and clove. Evening attend Kathakali dance or Kalaripayattu martial art performance."
        },
        {
          day: 4,
          title: "Thekkady to Alleppey Houseboat Cruise",
          description: "Board your private deluxe Kettuvallam houseboat at noon. Cruise through canals, paddy fields and serene lagoons while relishing hot local delicacies prepared on-board."
        },
        {
          day: 5,
          title: "Alleppey to Cochin Departure",
          description: "Disembark after breakfast. Transfer to Cochin Airport or Railway station with unforgettable memories."
        }
      ]
    },
    {
      id: "pkg-3",
      title: "Goa Beach Retreat & Sunset Catamaran Cruise",
      destination: "Goa, India",
      region: "West India",
      category: "Beaches",
      duration: "3 Nights / 4 Days",
      daysCount: 4,
      nightsCount: 3,
      price: 14499,
      originalPrice: 21999,
      rating: 4.7,
      reviewsCount: 320,
      badge: "Popular Deal",
      featured: true,
      image: "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80",
        "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Sun, golden sands, lively shacks, and rich Portuguese colonial history. Relax by your resort pool, discover historic churches in Old Goa, enjoy thrilling water sports at Calangute and Baga, and set sail on an energetic sunset cruise on the Mandovi River.",
      highlights: [
        "Stay at a 4-Star Beachside Resort with swimming pool and buffet breakfast",
        "Mandovi River Sunset Cruise with Goan folk music and DJ",
        "North Goa Beach Tour: Baga, Calangute, Anjuna & Chapora Fort",
        "South Goa Heritage Tour: Basilica of Bom Jesus & Mangueshi Temple",
        "Discount voucher for top beach shacks and water sports"
      ],
      inclusions: [
        "3 Nights accommodation at premium beach resort",
        "Daily Breakfast Buffet with multi-cuisine options",
        "Goa Airport (GOI/GOX) / Thivim railway station pickup & drop in private AC cab",
        "2 Days sightseeing in private air-conditioned vehicle",
        "Complimentary 1-hour Mandovi River cruise tickets"
      ],
      exclusions: [
        "Flights to/from Goa",
        "Water sports fees (Jet ski, parasailing, banana boat)",
        "Nightclub entry fees and personal food/beverages"
      ],
      stays: [
        { name: "Acron Waterfront Resort / Fairfield by Marriott Baga", rating: "4.7★ Beachside" }
      ],
      itinerary: [
        { day: 1, title: "Arrival in Goa & Beach Relaxation", description: "Arrive at Goa Airport and transfer to your resort. Unwind on the beach and enjoy vibrant beach shack music." },
        { day: 2, title: "North Goa Beaches & Fort Aguada", description: "Visit historic Fort Aguada, Chapora Fort, and spend the afternoon at Calangute, Baga, and Anjuna beaches." },
        { day: 3, title: "Old Goa Heritage & Sunset Cruise", description: "Explore the UNESCO-listed Basilica of Bom Jesus and Se Cathedral. In the evening, enjoy a sunset cruise on the Mandovi River." },
        { day: 4, title: "Departure", description: "Enjoy breakfast and beach walks before our cab transfers you to the airport." }
      ]
    },
    {
      id: "pkg-4",
      title: "Mystical Ladakh & Pangong Tso Expedition",
      destination: "Ladakh, India",
      region: "North India",
      category: "Adventure",
      duration: "6 Nights / 7 Days",
      daysCount: 7,
      nightsCount: 6,
      price: 32999,
      originalPrice: 44999,
      rating: 4.9,
      reviewsCount: 94,
      badge: "Adventure Top Pick",
      featured: true,
      image: "https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Embark on an epic Himalayan expedition through the high-altitude desert of Ladakh. Cross the dizzying Khardung La pass (17,582 ft), ride double-humped Bactrian camels on Nubra Valley's white sand dunes, and camp by the kaleidoscopic azure waters of Pangong Lake under stargazing skies.",
      highlights: [
        "Lakeside luxury Swiss cottage tent stay at Pangong Tso",
        "Double-humped camel ride in Nubra Valley Hunder dunes",
        "Conquer Khardung La - one of the highest motorable passes",
        "Visit Magnetic Hill, Sangam (Indus & Zanskar rivers confluence)",
        "Inner Line Permits & oxygen cylinder support vehicle included"
      ],
      inclusions: [
        "6 Nights Accommodations (4N Leh, 1N Nubra Deluxe Camp, 1N Pangong Camp)",
        "Daily Breakfast & Dinners throughout the tour",
        "Dedicated SUV (Innova / Scorpio) for all transfers & excursions",
        "All Inner line permits, Wildlife fees & Green entry passes",
        "Oxygen cylinder in vehicle for high altitude emergencies"
      ],
      exclusions: [
        "Airfare to/from Leh Airport (IXL)",
        "Personal rafting, camel ride fees or tips",
        "Lunch and any additional snacks"
      ],
      stays: [
        { name: "The Grand Dragon Ladakh / Singge Palace, Leh", rating: "4.8★ Luxury" },
        { name: "Nubra Valley Eco Luxury Camp", rating: "4.8★ Camp Experience" },
        { name: "Pangong Star Glamping Tents", rating: "4.9★ Lakeside Camp" }
      ],
      itinerary: [
        { day: 1, title: "Leh Arrival & Acclimatization", description: "Arrive at Leh Airport (11,500 ft). Complete day rest for proper high-altitude acclimatization. Evening stroll in Leh Bazaar." },
        { day: 2, title: "Sham Valley Tour & Magnetic Hill", description: "Visit Hall of Fame, Magnetic Hill, Gurudwara Pathar Sahib and Sangam confluence of Indus and Zanskar rivers." },
        { day: 3, title: "Leh to Nubra Valley via Khardung La", description: "Drive over legendary Khardung La pass. Arrive in Hunder and enjoy camel safari on the sand dunes. Overnight stay in Nubra camp." },
        { day: 4, title: "Nubra to Pangong Tso via Shyok River", description: "Travel along the dramatic Shyok river route directly to the turquoise blue Pangong Lake. Witness colors changing during sunset." },
        { day: 5, title: "Pangong Lake to Leh via Chang La", description: "Marvel at early morning sunrise over Pangong. Drive back to Leh crossing the mighty Chang La pass." },
        { day: 6, title: "Monasteries & Shey Palace Tour", description: "Visit Thiksey Monastery, Shey Palace, and Rancho's 3-Idiots school. Farewell dinner in Leh." },
        { day: 7, title: "Leh Airport Departure", description: "Transfer to Leh Airport for departure with memories of a lifetime." }
      ]
    },
    {
      id: "pkg-5",
      title: "Royal Rajasthan: Jaipur, Jodhpur & Udaipur",
      destination: "Rajasthan, India",
      region: "North India",
      category: "Heritage",
      duration: "5 Nights / 6 Days",
      daysCount: 6,
      nightsCount: 5,
      price: 26499,
      originalPrice: 36999,
      rating: 4.8,
      reviewsCount: 112,
      badge: "Royal Special",
      featured: false,
      image: "https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Step back into the era of valiant Maharajas and regal palaces. Explore Jaipur's Amber Fort and Hawa Mahal, Jodhpur's towering Mehrangarh Fort, and cruise across romantic Lake Pichola in the City of Lakes, Udaipur.",
      highlights: [
        "Stay at authentic heritage Haveli and luxury 4-Star palaces",
        "Boat cruise on Lake Pichola with view of Jag Mandir Palace",
        "Amber Fort jeep ride and Sound & Light show in Jaipur",
        "Panoramic view of the Blue City from Mehrangarh Fort",
        "Traditional Rajasthani Thali dinner feast"
      ],
      inclusions: [
        "5 Nights Deluxe Heritage Hotel Stays (2N Jaipur, 1N Jodhpur, 2N Udaipur)",
        "Daily Breakfast & Traditional Royal Dinners",
        "Private AC Sedan/Innova for intercity journeys and city tours",
        "Boat ride tickets on Lake Pichola",
        "Monument guide assistance at major forts"
      ],
      exclusions: ["Flights/train tickets", "Monument entry tickets", "Personal tips"],
      stays: [
        { name: "Alsisar Haveli, Jaipur", rating: "4.8★ Heritage" },
        { name: "Ajit Bhawan Palace, Jodhpur", rating: "4.7★ Heritage" },
        { name: "Fateh Garh Palace, Udaipur", rating: "4.9★ Lake View" }
      ],
      itinerary: [
        { day: 1, title: "Jaipur Pink City Arrival & City Palace", description: "Arrive in Jaipur, check into hotel. Visit City Palace and Jantar Mantar observatory." },
        { day: 2, title: "Amber Fort & Hawa Mahal", description: "Visit Amber Fort, Jal Mahal and photograph the iconic Hawa Mahal." },
        { day: 3, title: "Jaipur to Jodhpur (The Blue City)", description: "Drive to Jodhpur. Visit Mehrangarh Fort and Jaswant Thada marble cenotaph." },
        { day: 4, title: "Jodhpur to Udaipur via Ranakpur", description: "Drive to Udaipur stopping at the intricately carved Ranakpur Jain Temples. Evening boat ride on Lake Pichola." },
        { day: 5, title: "Udaipur Sightseeing", description: "Visit City Palace Udaipur, Saheliyon Ki Bari and Jagdish Temple." },
        { day: 6, title: "Departure", description: "Transfer to Udaipur Airport or railway station for onward journey." }
      ]
    },
    {
      id: "pkg-6",
      title: "Bali Tropical Bliss & Ubud Cultural Sanctuary",
      destination: "Bali, Indonesia",
      region: "International",
      category: "International",
      duration: "6 Nights / 7 Days",
      daysCount: 7,
      nightsCount: 6,
      price: 45999,
      originalPrice: 62000,
      rating: 4.9,
      reviewsCount: 160,
      badge: "International Favorite",
      featured: true,
      image: "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Surround yourself with Bali's emerald rice terraces, sacred waterfalls, ancient sea temples, and vibrant coastal culture. Stay in a private pool villa in Seminyak and a rainforest resort in Ubud.",
      highlights: [
        "Private Pool Villa stay in Seminyak with floating breakfast experience",
        "Ubud Bali Swing and Tegallalang Rice Terrace exploration",
        "Tanah Lot sunset temple and Uluwatu Kecak Fire Dance",
        "Snorkeling tour at Nusa Penida Island (Kelingking 'T-Rex' Beach)",
        "Complimentary 60-minute Balinese Spa massage"
      ],
      inclusions: [
        "6 Nights stay (3N Ubud Rainforest Resort, 3N Seminyak Private Pool Villa)",
        "Daily Breakfast + 1 Special Floating Breakfast",
        "Private AC car with English-speaking chauffeur-guide",
        "Fast boat transfers and island tour to Nusa Penida",
        "All temple entrance tickets and Bali Swing admission"
      ],
      exclusions: ["International flights to Denpasar (DPS)", "Indonesian Visa on arrival ($35 USD)"],
      stays: [
        { name: "Kamandalu Ubud Resort", rating: "4.9★ Rainforest Sanctuary" },
        { name: "The Seminyak Beach Resort & Spa", rating: "4.8★ Beachfront Villa" }
      ],
      itinerary: [
        { day: 1, title: "Denpasar Arrival & Ubud Check-in", description: "Warm Balinese flower welcome at Ngurah Rai Airport. Transfer to Ubud resort." },
        { day: 2, title: "Ubud Rice Terraces, Waterfalls & Bali Swing", description: "Visit Tegallalang rice fields, famous jungle swing, and Tegenungan waterfall." },
        { day: 3, title: "Kintamani Volcano & Coffee Plantation", description: "Panoramic view of Mount Batur volcano and taste Luwak artisan coffee." },
        { day: 4, title: "Nusa Penida Island Tour to Seminyak", description: "Speedboat to Nusa Penida. Visit Kelingking Beach and Angel's Billabong. Transfer to Seminyak Villa." },
        { day: 5, title: "Tanah Lot Temple & Sunset Shacks", description: "Visit iconic ocean temple Tanah Lot. Evening at famous Seminyak beach club." },
        { day: 6, title: "Uluwatu Temple & Kecak Dance", description: "Cliff-top Uluwatu temple and dramatic sunset Kecak fire dance." },
        { day: 7, title: "Airport Departure", description: "Souvenir shopping in Kuta before transfer to airport." }
      ]
    },
    {
      id: "pkg-7",
      title: "Dazzling Dubai & Desert Dunes Safari",
      destination: "Dubai, UAE",
      region: "International",
      category: "Luxury",
      duration: "4 Nights / 5 Days",
      daysCount: 5,
      nightsCount: 4,
      price: 49999,
      originalPrice: 68000,
      rating: 4.8,
      reviewsCount: 230,
      badge: "Luxury Escape",
      featured: true,
      image: "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Experience the glittering luxury of Dubai. Visit the 124th floor of Burj Khalifa, cruise along Dubai Marina on a luxury dhow with dinner, cross golden sand dunes in a 4x4 Land Cruiser, and visit the futuristic Museum of the Future.",
      highlights: [
        "Burj Khalifa 124th & 125th Floor Observation Deck Tickets",
        "4x4 Desert Safari with dune bashing, camel rides, belly dance & BBQ dinner",
        "Dubai Marina Luxury Dhow Cruise with international buffet dinner",
        "City tour covering Dubai Frame, Palm Jumeirah and Atlantis View Point",
        "Shopping at Dubai Mall and Gold Souk"
      ],
      inclusions: [
        "4 Nights stay at 4-Star or 5-Star Central Hotel in Dubai",
        "Daily Breakfast Buffet",
        "Dubai International Airport (DXB) return transfers in private cab",
        "All tours and activities mentioned in highlights",
        "UAE Tourist Visa with insurance included"
      ],
      exclusions: ["International flights", "Tourism Dirham fee paid at hotel (~$4/night)"],
      stays: [
        { name: "Grand Excelsior / Voco Dubai", rating: "4.8★ Luxury City Hotel" }
      ],
      itinerary: [
        { day: 1, title: "Dubai Arrival & Marina Dhow Cruise", description: "Arrival at DXB, hotel check-in. Evening Marina Dhow Cruise with live entertainment." },
        { day: 2, title: "Half Day City Tour & Burj Khalifa", description: "Tour Palm Jumeirah, Dubai Frame and visit top of Burj Khalifa and Dubai Mall fountain show." },
        { day: 3, title: "Desert Safari Extravaganza", description: "Afternoon desert safari with 4x4 dune bashing, sandboarding, camel rides, and BBQ dinner under stars." },
        { day: 4, title: "Museum of the Future & Shopping", description: "Visit the world-acclaimed Museum of the Future and shop at the historic Gold & Spice Souks." },
        { day: 5, title: "Departure", description: "Transfer to Dubai Airport for departure flight." }
      ]
    },
    {
      id: "pkg-8",
      title: "Scenic Swiss Alps & Lucerne Romance",
      destination: "Swiss Alps, Switzerland",
      region: "International",
      category: "Luxury",
      duration: "7 Nights / 8 Days",
      daysCount: 8,
      nightsCount: 7,
      price: 129999,
      originalPrice: 165000,
      rating: 5.0,
      reviewsCount: 64,
      badge: "Ultra Premium",
      featured: false,
      image: "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?auto=format&fit=crop&w=1000&q=80",
      gallery: [
        "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?auto=format&fit=crop&w=1000&q=80"
      ],
      overview: "Live your cinematic dream amidst snow-crested Swiss peaks. Explore Lucerne's Chapel Bridge, take the cogwheel train to Jungfraujoch 'Top of Europe', boat ride on Lake Brienz, and indulge in artisan Swiss chocolates and cheese in Interlaken.",
      highlights: [
        "8-Day 1st Class Swiss Travel Rail Pass with unlimited trains & boats",
        "Jungfraujoch 'Top of Europe' excursion to 11,333 feet",
        "Mount Titlis cable car with 360-degree rotating Rotair and Ice Cliff Walk",
        "Lake Lucerne panoramic scenic steamboat cruise",
        "Stays in charming Swiss chalets with mountain balconies"
      ],
      inclusions: [
        "7 Nights Deluxe 4-Star Hotel stays (3N Lucerne, 4N Interlaken)",
        "Daily Swiss Buffet Breakfasts",
        "Swiss Travel Rail Pass covering all domestic trains and transit",
        "Jungfraujoch and Mount Titlis excursion passes",
        "Swiss Schengen Visa assistance"
      ],
      exclusions: ["International flights to Zurich/Geneva", "Meals other than breakfast"],
      stays: [
        { name: "Hotel Schweizerhof, Lucerne", rating: "4.9★ Historic Luxury" },
        { name: "Victoria-Jungfrau Grand Hotel, Interlaken", rating: "5.0★ Alpine Icon" }
      ],
      itinerary: [
        { day: 1, title: "Zurich to Lucerne", description: "Arrive at Zurich Airport. Board train to Lucerne and walk across Chapel Bridge." },
        { day: 2, title: "Mount Titlis & Glacier Cave", description: "Ascend Mt Titlis, cross Europe's highest suspension bridge." },
        { day: 3, title: "Lake Lucerne Cruise & Interlaken", description: "Cruise Lake Lucerne, then board GoldenPass train to Interlaken." },
        { day: 4, title: "Jungfraujoch - Top of Europe", description: "Cogwheel train journey up into the Eiger glacier to Jungfraujoch." },
        { day: 5, title: "Lauterbrunnen 72 Waterfalls & Grindelwald", description: "Visit the postcard-perfect valley of Lauterbrunnen and Grindelwald First." },
        { day: 6, title: "Lake Brienz & Giessbach Falls", description: "Turquoise cruise on Lake Brienz and visit historic Giessbach Grand Hotel." },
        { day: 7, title: "Bern Old Town Day Tour", description: "Visit Switzerland's UNESCO capital Bern with its medieval clock tower." },
        { day: 8, title: "Departure", description: "Train to Zurich Airport for departure flight." }
      ]
    }
  ],

  initialBookings: [
    {
      id: "TE-2026-8941",
      packageId: "pkg-1",
      packageTitle: "Mesmerizing Kashmir & Dal Lake Paradise",
      destination: "Kashmir, India",
      image: "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=600&q=80",
      travelDate: "2026-11-28",
      returnDate: "2026-12-03",
      travelers: 2,
      guestName: "Arjun Sharma",
      guestEmail: "arjun.sharma@example.com",
      guestPhone: "+91 98765 43210",
      totalAmount: 52498,
      status: "Upcoming",
      paymentStatus: "Paid",
      paymentMethod: "UPI / Google Pay",
      createdAt: "2026-11-02"
    },
    {
      id: "TE-2026-7512",
      packageId: "pkg-3",
      packageTitle: "Goa Beach Retreat & Sunset Catamaran Cruise",
      destination: "Goa, India",
      image: "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=600&q=80",
      travelDate: "2026-10-14",
      returnDate: "2026-10-17",
      travelers: 2,
      guestName: "Priya Nair",
      guestEmail: "priya.nair@example.com",
      guestPhone: "+91 91234 56789",
      totalAmount: 28998,
      status: "Completed",
      paymentStatus: "Paid",
      paymentMethod: "Credit Card (HDFC)",
      createdAt: "2026-09-20"
    },
    {
      id: "TE-2026-6204",
      packageId: "pkg-2",
      packageTitle: "Enchanting Kerala Backwaters & Munnar Hills",
      destination: "Kerala, India",
      image: "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=600&q=80",
      travelDate: "2026-08-10",
      returnDate: "2026-08-15",
      travelers: 3,
      guestName: "Rahul Verma",
      guestEmail: "rahul.v@example.com",
      guestPhone: "+91 99887 76655",
      totalAmount: 59997,
      status: "Completed",
      paymentStatus: "Paid",
      paymentMethod: "Net Banking",
      createdAt: "2026-07-15"
    }
  ]
};

// --- INITIALIZE LOCAL STORAGE & BACKEND SYNC ---
async function initStorage() {
  if (!localStorage.getItem("tripEaseBookings")) {
    localStorage.setItem("tripEaseBookings", JSON.stringify(TRIPEASE_DATA.initialBookings));
  }
  if (!localStorage.getItem("tripEaseWishlist")) {
    localStorage.setItem("tripEaseWishlist", JSON.stringify(["pkg-1", "pkg-6"]));
  }
  if (!localStorage.getItem("tripEasePackages")) {
    localStorage.setItem("tripEasePackages", JSON.stringify(TRIPEASE_DATA.packages));
  }

  // Asynchronous sync from Spring Boot REST API
  try {
    const pkgResp = await fetch(`${API_BASE_URL}/packages`);
    if (pkgResp.ok) {
      const pkgData = await pkgResp.json();
      if (pkgData.data && pkgData.data.length > 0) {
        localStorage.setItem("tripEasePackages", JSON.stringify(pkgData.data));
      }
    }
  } catch (err) {
    // Backend offline, fallback to local cache
  }

  try {
    const destResp = await fetch(`${API_BASE_URL}/destinations`);
    if (destResp.ok) {
      const destData = await destResp.json();
      if (destData.data && destData.data.length > 0) {
        TRIPEASE_DATA.destinations = destData.data;
      }
    }
  } catch (err) {}

  try {
    const bookResp = await fetch(`${API_BASE_URL}/bookings`);
    if (bookResp.ok) {
      const bookData = await bookResp.json();
      if (bookData.data && bookData.data.length > 0) {
        localStorage.setItem("tripEaseBookings", JSON.stringify(bookData.data));
      }
    }
  } catch (err) {}
}

// Get stored packages
function getPackages() {
  const stored = localStorage.getItem("tripEasePackages");
  return stored ? JSON.parse(stored) : TRIPEASE_DATA.packages;
}

// Get stored bookings
function getBookings() {
  const stored = localStorage.getItem("tripEaseBookings");
  return stored ? JSON.parse(stored) : TRIPEASE_DATA.initialBookings;
}

// Format Currency to Indian Rupee
function formatINR(number) {
  return "₹" + Number(number).toLocaleString("en-IN");
}

// --- TOAST NOTIFICATION UTILITY ---
function showToast(message, type = "success") {
  let container = document.getElementById("toast-container");
  if (!container) {
    container = document.createElement("div");
    container.id = "toast-container";
    container.className = "toast-container";
    document.body.appendChild(container);
  }

  const toast = document.createElement("div");
  toast.className = `toast toast-${type}`;

  const iconClass = type === "success" 
    ? "fa-circle-check text-success" 
    : type === "error" 
    ? "fa-circle-exclamation text-danger" 
    : "fa-circle-info text-sky";

  toast.innerHTML = `
    <i class="fa-solid ${iconClass}" style="font-size: 1.3rem;"></i>
    <div style="flex: 1; font-size: 0.92rem; font-weight: 500;">${message}</div>
    <button onclick="this.parentElement.remove()" style="background: none; color: #94a3b8; font-size: 1rem;"><i class="fa-solid fa-xmark"></i></button>
  `;

  container.appendChild(toast);

  setTimeout(() => {
    toast.style.opacity = "0";
    toast.style.transform = "translateX(50px)";
    toast.style.transition = "all 0.4s ease";
    setTimeout(() => toast.remove(), 400);
  }, 4000);
}

// --- NAVBAR & AUTH STATE HANDLER ---
function setupNavbar() {
  // Mobile hamburger toggle
  const mobileBtn = document.getElementById("mobileToggle");
  const navMenu = document.getElementById("navMenu");
  if (mobileBtn && navMenu) {
    mobileBtn.addEventListener("click", () => {
      navMenu.classList.toggle("active");
      mobileBtn.innerHTML = navMenu.classList.contains("active") 
        ? '<i class="fa-solid fa-xmark"></i>' 
        : '<i class="fa-solid fa-bars"></i>';
    });
  }

  // Update Wishlist badge
  updateWishlistBadge();

  // User Profile or Login CTA in Navbar
  const userContainer = document.getElementById("navUserSection");
  if (userContainer) {
    const userJson = localStorage.getItem("tripEaseUser");
    if (userJson) {
      const user = JSON.parse(userJson);
      userContainer.innerHTML = `
        <div class="user-nav-profile" id="userProfileToggle">
          <div class="user-avatar">${user.name ? user.name.charAt(0).toUpperCase() : 'U'}</div>
          <span class="user-name-text">${user.name.split(' ')[0]}</span>
          <i class="fa-solid fa-chevron-down text-muted" style="font-size: 0.75rem;"></i>
          
          <div class="user-dropdown" id="userProfileDropdown">
            <div style="padding: 0.6rem 0.8rem; font-size: 0.82rem; color: #64748b;">
              Signed in as<br><strong style="color: #0f172a;">${user.email}</strong>
            </div>
            <div class="user-dropdown-divider"></div>
            <a href="my-trips.html" class="user-dropdown-item"><i class="fa-solid fa-suitcase-rolling"></i> My Trips</a>
            <a href="admin.html" class="user-dropdown-item"><i class="fa-solid fa-gauge-high"></i> Admin Portal</a>
            <div class="user-dropdown-divider"></div>
            <a href="javascript:void(0)" onclick="logoutUser()" class="user-dropdown-item text-danger"><i class="fa-solid fa-arrow-right-from-bracket"></i> Sign Out</a>
          </div>
        </div>
      `;

      const toggle = document.getElementById("userProfileToggle");
      const dropdown = document.getElementById("userProfileDropdown");
      if (toggle && dropdown) {
        toggle.addEventListener("click", (e) => {
          e.stopPropagation();
          dropdown.classList.toggle("show");
        });
        document.addEventListener("click", () => dropdown.classList.remove("show"));
      }
    } else {
      userContainer.innerHTML = `
        <a href="login.html" class="btn btn-outline-navy btn-sm"><i class="fa-regular fa-user"></i> Login</a>
        <a href="register.html" class="btn btn-primary btn-sm">Register</a>
      `;
    }
  }

  // Navbar shadow on scroll
  window.addEventListener("scroll", () => {
    const nav = document.querySelector(".navbar");
    if (nav) {
      if (window.scrollY > 30) {
        nav.classList.add("scrolled");
      } else {
        nav.classList.remove("scrolled");
      }
    }
  });
}

function updateWishlistBadge() {
  const badge = document.getElementById("navWishlistCount");
  if (badge) {
    const wishlist = JSON.parse(localStorage.getItem("tripEaseWishlist") || "[]");
    badge.textContent = wishlist.length;
  }
}

function toggleWishlist(packageId, event) {
  if (event) event.stopPropagation();
  let wishlist = JSON.parse(localStorage.getItem("tripEaseWishlist") || "[]");
  const idx = wishlist.indexOf(packageId);
  if (idx > -1) {
    wishlist.splice(idx, 1);
    showToast("Removed package from your saved wishlist", "info");
  } else {
    wishlist.push(packageId);
    showToast("Added package to your saved wishlist! ❤️", "success");
  }
  localStorage.setItem("tripEaseWishlist", JSON.stringify(wishlist));
  updateWishlistBadge();

  // update all heart icons on the page
  document.querySelectorAll(`[data-wishlist-id="${packageId}"]`).forEach(btn => {
    if (wishlist.includes(packageId)) {
      btn.classList.add("active");
      btn.innerHTML = '<i class="fa-solid fa-heart"></i>';
    } else {
      btn.classList.remove("active");
      btn.innerHTML = '<i class="fa-regular fa-heart"></i>';
    }
  });
}

function logoutUser() {
  localStorage.removeItem("tripEaseUser");
  showToast("You have been signed out successfully.", "info");
  setTimeout(() => {
    const path = window.location.pathname.toLowerCase();
    if (path.includes("booking.html") || path.includes("package-details.html") || path.includes("my-trips.html") || path.includes("admin.html")) {
      window.location.href = "login.html";
    } else {
      window.location.reload();
    }
  }, 600);
}

function handleBookingClick(targetUrl, event) {
  if (event) {
    event.preventDefault();
    event.stopPropagation();
  }
  const user = localStorage.getItem("tripEaseUser");
  if (!user) {
    window.location.href = `login.html?redirect=${encodeURIComponent(targetUrl)}`;
    return;
  }
  window.location.href = targetUrl;
}

function handlePackageAction(targetUrl, event) {
  return handleBookingClick(targetUrl, event);
}

function handleSocialQuickLogin(name, email) {
  const user = { name: name, email: email, phone: "+91 98765 43210", role: "USER" };
  localStorage.setItem("tripEaseUser", JSON.stringify(user));
  showToast(`Welcome, ${name}!`, "success");
  const targetUrl = getPostLoginRedirectUrl(user);
  setTimeout(() => {
    window.location.href = targetUrl;
  }, 600);
}

function getPostLoginRedirectUrl(user) {
  if (user && user.role === "ADMIN") {
    return "admin.html";
  }
  const params = new URLSearchParams(window.location.search);
  let redirectUrl = params.get("redirect");
  const destParam = params.get("dest");
  const claimParam = params.get("claim");
  let targetUrl = "index.html";

  if (redirectUrl) {
    if (!redirectUrl.includes("?") && params.get("id")) {
      redirectUrl += `?id=${encodeURIComponent(params.get("id"))}`;
    }
    if (params.get("date") && !redirectUrl.includes("date=")) {
      redirectUrl += (redirectUrl.includes("?") ? "&" : "?") + `date=${encodeURIComponent(params.get("date"))}`;
    }
    if (params.get("guests") && !redirectUrl.includes("guests=")) {
      redirectUrl += (redirectUrl.includes("?") ? "&" : "?") + `guests=${encodeURIComponent(params.get("guests"))}`;
    }
    targetUrl = redirectUrl;
    const qParts = [];
    if (destParam && !targetUrl.includes("dest=")) qParts.push(`dest=${encodeURIComponent(destParam)}`);
    if (claimParam && !targetUrl.includes("claim=")) qParts.push(`claim=${encodeURIComponent(claimParam)}`);
    if (qParts.length) targetUrl += (targetUrl.includes("?") ? "&" : "?") + qParts.join("&");
  } else if (params.get("id")) {
    targetUrl = `booking.html?id=${encodeURIComponent(params.get("id"))}`;
    if (params.get("date")) targetUrl += `&date=${encodeURIComponent(params.get("date"))}`;
    if (params.get("guests")) targetUrl += `&guests=${encodeURIComponent(params.get("guests"))}`;
  } else if (claimParam) {
    targetUrl = `index.html?claim=${encodeURIComponent(claimParam)}`;
  }
  return targetUrl;
}

// --- RENDER DESTINATION CARDS ---
function renderDestinationsGrid(destinations, containerId) {
  const container = document.getElementById(containerId);
  if (!container) return;

  if (destinations.length === 0) {
    container.innerHTML = `
      <div style="grid-column: 1 / -1; text-align: center; padding: 4rem 1rem;">
        <i class="fa-solid fa-compass" style="font-size: 3rem; color: #cbd5e1; margin-bottom: 1rem;"></i>
        <h3>No destinations found matching your criteria</h3>
        <p class="text-muted">Try adjusting your filters or search keywords.</p>
      </div>
    `;
    return;
  }

  container.innerHTML = destinations.map(dest => `
    <div class="destination-card" onclick="window.location.href='packages.html?destination=${encodeURIComponent(dest.name)}'">
      <img src="${dest.image}" alt="${dest.name}" class="destination-img" loading="lazy">
      <div class="destination-overlay">
        <div class="destination-badge-top">
          <span class="badge badge-pill-soft"><i class="fa-solid fa-location-dot"></i> ${dest.region}</span>
          <span class="badge badge-orange">${dest.toursCount} Tours</span>
        </div>
        <div class="destination-info">
          <span class="destination-country"><i class="fa-solid fa-flag"></i> ${dest.country}</span>
          <h3 class="destination-name">${dest.name}</h3>
          <p class="destination-desc-short">${dest.description}</p>
          <div class="destination-meta">
            <div class="destination-price">
              Starting from
              <strong>${formatINR(dest.price)}</strong>
            </div>
            <button class="btn btn-sky btn-sm">Explore <i class="fa-solid fa-arrow-right"></i></button>
          </div>
        </div>
      </div>
    </div>
  `).join("");
}

// --- RENDER PACKAGE CARDS ---
function renderPackagesGrid(packages, containerId) {
  const container = document.getElementById(containerId);
  if (!container) return;

  const wishlist = JSON.parse(localStorage.getItem("tripEaseWishlist") || "[]");

  if (packages.length === 0) {
    container.innerHTML = `
      <div style="grid-column: 1 / -1; text-align: center; padding: 5rem 1rem; background: #ffffff; border-radius: 16px; border: 1px dashed #cbd5e1;">
        <i class="fa-solid fa-plane-slash" style="font-size: 3.5rem; color: #94a3b8; margin-bottom: 1rem;"></i>
        <h3>No matching travel packages found</h3>
        <p class="text-muted" style="max-width: 450px; margin: 0 auto 1.5rem;">We couldn't find any tours matching all your active filter conditions. Try clearing some filters or searching for another place!</p>
        <button onclick="resetPackageFilters()" class="btn btn-primary btn-sm"><i class="fa-solid fa-rotate-left"></i> Reset All Filters</button>
      </div>
    `;
    return;
  }

  container.innerHTML = packages.map(pkg => {
    const isWishlisted = wishlist.includes(pkg.id);
    return `
      <div class="package-card" data-pkg-id="${pkg.id}">
        <div class="package-thumb">
          <img src="${pkg.image}" alt="${pkg.title}" loading="lazy">
          <span class="badge ${pkg.badge.includes('Best') ? 'badge-orange' : 'badge-sky'} package-badge-tag">
            <i class="fa-solid fa-bolt"></i> ${pkg.badge}
          </span>
          <button class="package-wishlist-btn ${isWishlisted ? 'active' : ''}" 
                  data-wishlist-id="${pkg.id}" 
                  onclick="toggleWishlist('${pkg.id}', event)" 
                  title="Save to Wishlist">
            <i class="fa-${isWishlisted ? 'solid' : 'regular'} fa-heart"></i>
          </button>
          <div class="package-duration-strip">
            <span><i class="fa-regular fa-clock"></i> ${pkg.duration}</span>
            <span><i class="fa-solid fa-hotel"></i> 4★ & 5★ Stays</span>
          </div>
        </div>

        <div class="package-body">
          <div class="package-location">
            <i class="fa-solid fa-location-dot"></i> ${pkg.destination}
          </div>
          <h3 class="package-title">
            <a href="package-details.html?id=${pkg.id}">${pkg.title}</a>
          </h3>

          <div class="package-rating">
            <span class="rating-stars"><i class="fa-solid fa-star"></i></span>
            <span class="rating-val">${pkg.rating}</span>
            <span class="rating-count">(${pkg.reviewsCount} reviews)</span>
          </div>

          <div class="package-inclusions">
            <span class="inc-pill"><i class="fa-solid fa-utensils text-orange"></i> Meals</span>
            <span class="inc-pill"><i class="fa-solid fa-car text-sky"></i> Transfers</span>
            <span class="inc-pill"><i class="fa-solid fa-binoculars text-success"></i> Sightseeing</span>
            <span class="inc-pill"><i class="fa-solid fa-bed text-navy"></i> Stays</span>
          </div>

          <div class="package-footer">
            <div class="package-price-wrap">
              <span class="price-old">${formatINR(pkg.originalPrice)}</span>
              <span class="price-current">${formatINR(pkg.price)}</span>
              <span class="price-unit">per person (twin sharing)</span>
            </div>
            <div style="display: flex; gap: 0.5rem;">
              <a href="package-details.html?id=${pkg.id}" class="btn btn-navy btn-sm">Details</a>
              <a href="booking.html?id=${pkg.id}" onclick="handleBookingClick('booking.html?id=${pkg.id}', event)" class="btn btn-primary btn-sm">Book</a>
            </div>
          </div>
        </div>
      </div>
    `;
  }).join("");
}

// --- PACKAGE DETAILS PAGE LOGIC ---
function setupPackageDetailsPage() {
  const params = new URLSearchParams(window.location.search);
  const pkgId = params.get("id") || "pkg-1";
  const allPackages = getPackages();
  const pkg = allPackages.find(p => p.id === pkgId) || allPackages[0];

  // Set Page Title
  document.title = `${pkg.title} - TripEase`;

  // Update Breadcrumb & Header
  const titleEl = document.getElementById("detailsTitle");
  const destEl = document.getElementById("detailsDestination");
  const badgeEl = document.getElementById("detailsBadge");
  const ratingEl = document.getElementById("detailsRating");
  const reviewsEl = document.getElementById("detailsReviewsCount");
  const durationEl = document.getElementById("detailsDuration");

  if (titleEl) titleEl.textContent = pkg.title;
  if (destEl) destEl.innerHTML = `<i class="fa-solid fa-location-dot"></i> ${pkg.destination}`;
  if (badgeEl) badgeEl.textContent = pkg.badge;
  if (ratingEl) ratingEl.textContent = pkg.rating;
  if (reviewsEl) reviewsEl.textContent = `(${pkg.reviewsCount} verified traveler reviews)`;
  if (durationEl) durationEl.textContent = pkg.duration;

  // Render Image Gallery
  const galleryEl = document.getElementById("detailsGallery");
  if (galleryEl && pkg.gallery) {
    galleryEl.innerHTML = `
      <div class="gallery-main" onclick="openPhotoModal('${pkg.gallery[0]}')">
        <img src="${pkg.gallery[0]}" alt="${pkg.title}">
      </div>
      <div class="gallery-thumb" onclick="openPhotoModal('${pkg.gallery[1] || pkg.gallery[0]}')">
        <img src="${pkg.gallery[1] || pkg.gallery[0]}" alt="Gallery Image 2">
      </div>
      <div class="gallery-thumb" onclick="openPhotoModal('${pkg.gallery[2] || pkg.gallery[0]}')">
        <img src="${pkg.gallery[2] || pkg.gallery[0]}" alt="Gallery Image 3">
      </div>
      <div class="gallery-thumb" onclick="openPhotoModal('${pkg.gallery[3] || pkg.gallery[0]}')">
        <img src="${pkg.gallery[3] || pkg.gallery[0]}" alt="Gallery Image 4">
      </div>
      <div class="gallery-thumb" style="position: relative;" onclick="openPhotoModal('${pkg.gallery[0]}')">
        <img src="${pkg.gallery[0]}" alt="Gallery Image 5">
        <div style="position: absolute; inset: 0; background: rgba(15,36,68,0.7); color: #fff; display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1rem;">
          <i class="fa-solid fa-camera" style="margin-right: 0.5rem;"></i> View All
        </div>
      </div>
    `;
  }

  // Render Overview & Highlights
  const overviewEl = document.getElementById("detailsOverview");
  if (overviewEl) overviewEl.textContent = pkg.overview;

  const highlightsEl = document.getElementById("detailsHighlights");
  if (highlightsEl && pkg.highlights) {
    highlightsEl.innerHTML = pkg.highlights.map(h => `
      <li style="display: flex; gap: 0.75rem; margin-bottom: 0.75rem; font-size: 0.95rem;">
        <i class="fa-solid fa-circle-check text-orange" style="margin-top: 0.25rem;"></i>
        <span>${h}</span>
      </li>
    `).join("");
  }

  // Render Day-by-Day Itinerary Accordion
  const itineraryEl = document.getElementById("detailsItinerary");
  if (itineraryEl && pkg.itinerary) {
    itineraryEl.innerHTML = pkg.itinerary.map((item, idx) => `
      <div class="timeline-day-card ${idx === 0 ? 'active' : ''}" id="day-card-${idx}">
        <div class="day-card-header" onclick="toggleItineraryDay(${idx})">
          <div style="display: flex; align-items: center;">
            <span class="day-badge">DAY ${item.day}</span>
            <span>${item.title}</span>
          </div>
          <i class="fa-solid ${idx === 0 ? 'fa-chevron-up' : 'fa-chevron-down'} text-muted" id="day-icon-${idx}"></i>
        </div>
        <div class="day-card-body" id="day-body-${idx}" style="display: ${idx === 0 ? 'block' : 'none'};">
          <p>${item.description}</p>
        </div>
      </div>
    `).join("");
  }

  // Render Inclusions & Exclusions
  const incEl = document.getElementById("detailsInclusions");
  if (incEl && pkg.inclusions) {
    incEl.innerHTML = pkg.inclusions.map(inc => `
      <li><i class="fa-solid fa-circle-check"></i> <span>${inc}</span></li>
    `).join("");
  }

  const excEl = document.getElementById("detailsExclusions");
  if (excEl && pkg.exclusions) {
    excEl.innerHTML = pkg.exclusions.map(exc => `
      <li><i class="fa-solid fa-circle-xmark"></i> <span>${exc}</span></li>
    `).join("");
  }

  // Render Hotel Stays
  const staysEl = document.getElementById("detailsStays");
  if (staysEl && pkg.stays) {
    staysEl.innerHTML = pkg.stays.map(s => `
      <div style="background: var(--bg-subtle); padding: 1.25rem; border-radius: var(--radius-md); border-left: 4px solid var(--sky-blue); margin-bottom: 1rem;">
        <h4 style="font-size: 1.05rem; margin-bottom: 0.25rem;">${s.name}</h4>
        <span class="badge badge-sky">${s.rating}</span>
      </div>
    `).join("");
  }

  // Sticky Calculator Widget
  const stickyPriceEl = document.getElementById("stickyPrice");
  const stickyOldPriceEl = document.getElementById("stickyOldPrice");
  if (stickyPriceEl) stickyPriceEl.textContent = formatINR(pkg.price);
  if (stickyOldPriceEl) stickyOldPriceEl.textContent = formatINR(pkg.originalPrice);

  // Setup Live Price Calculator
  setupStickyCalculator(pkg);

  // Setup Book CTA link
  const bookBtn = document.getElementById("proceedToBookBtn");
  if (bookBtn) {
    bookBtn.addEventListener("click", () => {
      const travelDate = document.getElementById("calcDate") ? document.getElementById("calcDate").value : "";
      const guests = document.getElementById("calcGuests") ? document.getElementById("calcGuests").value : "2";
      const bookingUrl = `booking.html?id=${pkg.id}&date=${encodeURIComponent(travelDate)}&guests=${guests}`;
      const user = localStorage.getItem("tripEaseUser");
      if (!user) {
        window.location.href = `login.html?redirect=${encodeURIComponent(bookingUrl)}`;
        return;
      }
      window.location.href = bookingUrl;
    });
  }
}

function toggleItineraryDay(idx) {
  const card = document.getElementById(`day-card-${idx}`);
  const body = document.getElementById(`day-body-${idx}`);
  const icon = document.getElementById(`day-icon-${idx}`);
  if (!card || !body) return;

  const isOpen = body.style.display === "block";
  body.style.display = isOpen ? "none" : "block";
  card.classList.toggle("active", !isOpen);
  if (icon) {
    icon.className = isOpen ? "fa-solid fa-chevron-down text-muted" : "fa-solid fa-chevron-up text-muted";
  }
}

function setupStickyCalculator(pkg) {
  const guestsInput = document.getElementById("calcGuests");
  const roomSelect = document.getElementById("calcRoomType");
  const calcBaseTotalEl = document.getElementById("calcBaseTotal");
  const calcGstEl = document.getElementById("calcGst");
  const calcGrandTotalEl = document.getElementById("calcGrandTotal");

  function recalculate() {
    const guests = parseInt(guestsInput ? guestsInput.value : 2, 10) || 1;
    let roomMultiplier = 1;
    if (roomSelect && roomSelect.value === "deluxe") roomMultiplier = 1.15;
    if (roomSelect && roomSelect.value === "luxury") roomMultiplier = 1.35;

    const basePerPerson = pkg.price * roomMultiplier;
    const baseTotal = basePerPerson * guests;
    const gst = Math.round(baseTotal * 0.05); // 5% GST
    const grandTotal = baseTotal + gst;

    if (calcBaseTotalEl) calcBaseTotalEl.textContent = formatINR(baseTotal);
    if (calcGstEl) calcGstEl.textContent = formatINR(gst);
    if (calcGrandTotalEl) calcGrandTotalEl.textContent = formatINR(grandTotal);
  }

  if (guestsInput) guestsInput.addEventListener("input", recalculate);
  if (roomSelect) roomSelect.addEventListener("change", recalculate);
  recalculate();
}

// --- BOOKING PAGE LOGIC ---
function setupBookingPage() {
  const userJson = localStorage.getItem("tripEaseUser");
  if (!userJson) {
    const returnUrl = "booking.html" + window.location.search;
    window.location.replace(`login.html?redirect=${encodeURIComponent(returnUrl)}`);
    return;
  }

  const params = new URLSearchParams(window.location.search);
  const pkgId = params.get("id") || "pkg-1";
  const allPackages = getPackages();
  const pkg = allPackages.find(p => p.id === pkgId) || allPackages[0];

  // Autofill initial values from URL
  const initialDate = params.get("date") || "";
  const initialGuests = parseInt(params.get("guests") || "2", 10);

  const dateInput = document.getElementById("bookTravelDate");
  const guestsInput = document.getElementById("bookGuestsCount");
  if (dateInput && initialDate) dateInput.value = initialDate;
  if (guestsInput && initialGuests) guestsInput.value = initialGuests;

  // Default min date to tomorrow
  if (dateInput) {
    const tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    dateInput.min = tomorrow.toISOString().split("T")[0];
    if (!dateInput.value) {
      dateInput.value = tomorrow.toISOString().split("T")[0];
    }
  }

  // Populate Package Summary Card
  const summaryImg = document.getElementById("summaryPkgImg");
  const summaryTitle = document.getElementById("summaryPkgTitle");
  const summaryDest = document.getElementById("summaryPkgDest");
  const summaryDuration = document.getElementById("summaryPkgDuration");
  const summaryBaseRate = document.getElementById("summaryBaseRate");

  if (summaryImg) summaryImg.src = pkg.image;
  if (summaryTitle) summaryTitle.textContent = pkg.title;
  if (summaryDest) summaryDest.innerHTML = `<i class="fa-solid fa-location-dot"></i> ${pkg.destination}`;
  if (summaryDuration) summaryDuration.textContent = pkg.duration;
  if (summaryBaseRate) summaryBaseRate.textContent = `${formatINR(pkg.price)} × 1 person`;

  // Pre-fill user data if logged in
  const userJson = localStorage.getItem("tripEaseUser");
  if (userJson) {
    const user = JSON.parse(userJson);
    if (document.getElementById("leadName")) document.getElementById("leadName").value = user.name || "";
    if (document.getElementById("leadEmail")) document.getElementById("leadEmail").value = user.email || "";
    if (document.getElementById("leadPhone")) document.getElementById("leadPhone").value = user.phone || "";
  }

  // Coupon State
  let appliedCouponDiscount = 0;
  let activeCouponCode = "";

  // Calculation function
  function updateBookingTotals() {
    const guests = parseInt(guestsInput ? guestsInput.value : 2, 10) || 1;
    const baseTotal = pkg.price * guests;

    // Addons
    let addonsTotal = 0;
    const addonCheckboxes = document.querySelectorAll(".addon-checkbox:checked");
    addonCheckboxes.forEach(cb => {
      const pricePerPerson = parseInt(cb.dataset.price || 0, 10);
      addonsTotal += (pricePerPerson * guests);
    });

    // Subtotal
    const subtotal = baseTotal + addonsTotal;

    // Apply Coupon
    let discount = 0;
    if (activeCouponCode === "TRIPEASE2024") {
      discount = 2000;
    } else if (activeCouponCode === "WANDERLUST") {
      discount = Math.round(subtotal * 0.10); // 10%
    } else if (activeCouponCode === "EXPLORE500") {
      discount = 500;
    }
    appliedCouponDiscount = discount;

    const taxableAmount = Math.max(0, subtotal - discount);
    const gst = Math.round(taxableAmount * 0.05); // 5% GST
    const grandTotal = taxableAmount + gst;

    // Update DOM
    if (document.getElementById("summaryGuestsText")) {
      document.getElementById("summaryGuestsText").textContent = `${guests} Adult${guests > 1 ? 's' : ''}`;
    }
    if (document.getElementById("summaryBaseTotal")) {
      document.getElementById("summaryBaseTotal").textContent = formatINR(baseTotal);
    }
    if (document.getElementById("summaryAddonsTotal")) {
      document.getElementById("summaryAddonsTotal").textContent = formatINR(addonsTotal);
    }
    if (document.getElementById("summaryDiscountRow")) {
      if (discount > 0) {
        document.getElementById("summaryDiscountRow").style.display = "flex";
        document.getElementById("summaryDiscountAmount").textContent = `-${formatINR(discount)}`;
      } else {
        document.getElementById("summaryDiscountRow").style.display = "none";
      }
    }
    if (document.getElementById("summaryGst")) {
      document.getElementById("summaryGst").textContent = formatINR(gst);
    }
    if (document.getElementById("summaryGrandTotal")) {
      document.getElementById("summaryGrandTotal").textContent = formatINR(grandTotal);
    }

    return { guests, baseTotal, addonsTotal, discount, gst, grandTotal };
  }

  // Listeners for recalculation
  if (guestsInput) guestsInput.addEventListener("input", updateBookingTotals);
  document.querySelectorAll(".addon-checkbox").forEach(cb => {
    cb.addEventListener("change", (e) => {
      const card = e.target.closest(".addon-card");
      if (card) card.classList.toggle("selected", e.target.checked);
      updateBookingTotals();
    });
  });

  // Apply Coupon Button
  const couponBtn = document.getElementById("applyCouponBtn");
  const couponInput = document.getElementById("couponInput");
  const couponMessage = document.getElementById("couponMessage");

  if (couponBtn && couponInput) {
    couponBtn.addEventListener("click", () => {
      const code = couponInput.value.trim().toUpperCase();
      if (!code) {
        showToast("Please enter a promotional coupon code", "error");
        return;
      }
      if (["TRIPEASE2024", "WANDERLUST", "EXPLORE500"].includes(code)) {
        activeCouponCode = code;
        updateBookingTotals();
        showToast(`Promo Code ${code} applied successfully!`, "success");
        if (couponMessage) {
          couponMessage.innerHTML = `<span class="text-success"><i class="fa-solid fa-circle-check"></i> Code <strong>${code}</strong> applied!</span>`;
        }
      } else {
        showToast("Invalid or expired coupon code. Try TRIPEASE2024 or WANDERLUST", "error");
        if (couponMessage) {
          couponMessage.innerHTML = `<span class="text-danger"><i class="fa-solid fa-circle-xmark"></i> Invalid code. Use <strong>TRIPEASE2024</strong></span>`;
        }
      }
    });
  }

  // Payment method tab switcher
  const paymentTabs = document.querySelectorAll(".payment-tab");
  paymentTabs.forEach(tab => {
    tab.addEventListener("click", () => {
      paymentTabs.forEach(t => t.classList.remove("active"));
      tab.classList.add("active");

      const method = tab.dataset.method;
      document.querySelectorAll(".payment-fields-group").forEach(g => g.style.display = "none");
      const targetGroup = document.getElementById(`pay-group-${method}`);
      if (targetGroup) targetGroup.style.display = "block";
    });
  });

  // Multi-step navigation
  let currentStep = 1;
  const nextBtn = document.getElementById("nextStepBtn");
  const prevBtn = document.getElementById("prevStepBtn");
  const confirmBtn = document.getElementById("confirmBookingBtn");

  function showStep(step) {
    currentStep = step;
    document.querySelectorAll(".booking-step-content").forEach(el => el.style.display = "none");
    const activeSection = document.getElementById(`stepContent${step}`);
    if (activeSection) activeSection.style.display = "block";

    // Update Step Indicators
    for (let i = 1; i <= 3; i++) {
      const indicator = document.getElementById(`stepIndicator${i}`);
      if (indicator) {
        if (i === step) {
          indicator.className = "step-indicator active";
        } else if (i < step) {
          indicator.className = "step-indicator completed";
        } else {
          indicator.className = "step-indicator";
        }
      }
    }

    if (prevBtn) prevBtn.style.display = step === 1 ? "none" : "inline-flex";
    if (nextBtn) nextBtn.style.display = step === 3 ? "none" : "inline-flex";
    if (confirmBtn) confirmBtn.style.display = step === 3 ? "inline-flex" : "none";

    window.scrollTo({ top: 200, behavior: "smooth" });
  }

  if (nextBtn) {
    nextBtn.addEventListener("click", () => {
      if (currentStep === 1) {
        // Validate Step 1
        const leadName = document.getElementById("leadName")?.value.trim();
        const leadEmail = document.getElementById("leadEmail")?.value.trim();
        const leadPhone = document.getElementById("leadPhone")?.value.trim();
        const travelDate = document.getElementById("bookTravelDate")?.value;

        if (!leadName || !leadEmail || !leadPhone || !travelDate) {
          showToast("Please fill all traveler details and select a travel date", "error");
          return;
        }
        showStep(2);
      } else if (currentStep === 2) {
        showStep(3);
      }
    });
  }

  if (prevBtn) {
    prevBtn.addEventListener("click", () => {
      if (currentStep > 1) showStep(currentStep - 1);
    });
  }

  // Handle Booking Submission
  const bookingForm = document.getElementById("bookingMasterForm");
  if (bookingForm) {
    bookingForm.addEventListener("submit", async (e) => {
      e.preventDefault();

      const totals = updateBookingTotals();
      const leadName = document.getElementById("leadName").value.trim();
      const leadEmail = document.getElementById("leadEmail").value.trim();
      const leadPhone = document.getElementById("leadPhone").value.trim();
      const travelDate = document.getElementById("bookTravelDate").value;

      // Calculate return date
      const d = new Date(travelDate);
      d.setDate(d.getDate() + (pkg.daysCount || 5));
      const returnDate = d.toISOString().split("T")[0];

      // Active payment method
      const activeTab = document.querySelector(".payment-tab.active");
      const paymentMethod = activeTab ? activeTab.innerText.trim() : "UPI / Net Banking";

      // Fallback ID if offline
      let bookingId = "TE-" + new Date().getFullYear() + "-" + Math.floor(1000 + Math.random() * 9000);

      const bookingPayload = {
        packageId: pkg.id,
        bookingType: "PACKAGE",
        packageTitle: pkg.title,
        destination: pkg.destination,
        image: pkg.image,
        travelDate: travelDate,
        returnDate: returnDate,
        travelers: totals.guests,
        guestName: leadName,
        guestEmail: leadEmail,
        guestPhone: leadPhone,
        departureCity: document.getElementById("departureCity") ? document.getElementById("departureCity").value.trim() : "",
        specialRequests: document.getElementById("specialRequests") ? document.getElementById("specialRequests").value.trim() : "",
        totalAmount: totals.grandTotal,
        paymentMethod: paymentMethod
      };

      // Call Spring Boot REST API
      try {
        const resp = await fetch(`${API_BASE_URL}/bookings`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(bookingPayload)
        });
        if (resp.ok) {
          const result = await resp.json();
          if (result.success && result.data) {
            bookingId = result.data.id;
            const existingBookings = getBookings();
            existingBookings.unshift(result.data);
            localStorage.setItem("tripEaseBookings", JSON.stringify(existingBookings));
          }
        } else {
          throw new Error("API call failed");
        }
      } catch (err) {
        // Fallback to local storage if backend temporarily unavailable
        const newBooking = {
          ...bookingPayload,
          id: bookingId,
          status: "Upcoming",
          paymentStatus: "Paid",
          createdAt: new Date().toISOString().split("T")[0]
        };
        const existingBookings = getBookings();
        existingBookings.unshift(newBooking);
        localStorage.setItem("tripEaseBookings", JSON.stringify(existingBookings));
      }

      // Show Confirmation Modal
      const modal = document.getElementById("bookingSuccessModal");
      if (modal) {
        document.getElementById("confirmedBookingId").textContent = bookingId;
        document.getElementById("confirmedPkgTitle").textContent = pkg.title;
        document.getElementById("confirmedTravelDate").textContent = travelDate;
        document.getElementById("confirmedAmount").textContent = formatINR(totals.grandTotal);
        modal.classList.add("active");
      } else {
        showToast("Booking Confirmed Successfully!", "success");
        setTimeout(() => window.location.href = "my-trips.html", 1500);
      }
    });
  }

  // Initialize totals
  updateBookingTotals();
}

// --- MY TRIPS PAGE LOGIC ---
function setupMyTripsPage() {
  const userJson = localStorage.getItem("tripEaseUser");
  if (!userJson) {
    window.location.href = "login.html?redirect=my-trips.html";
    return;
  }

  const bookings = getBookings();
  const tripsContainer = document.getElementById("myTripsList");
  if (!tripsContainer) return;

  // Compute Trip Stats
  let upcomingCount = 0;
  let completedCount = 0;
  let totalSpent = 0;

  bookings.forEach(b => {
    if (b.status === "Upcoming") upcomingCount++;
    if (b.status === "Completed") completedCount++;
    totalSpent += Number(b.totalAmount) || 0;
  });

  const upcomingStat = document.getElementById("statUpcomingTrips");
  const completedStat = document.getElementById("statCompletedTrips");
  const totalSpentStat = document.getElementById("statTotalSpent");

  if (upcomingStat) upcomingStat.textContent = upcomingCount;
  if (completedStat) completedStat.textContent = completedCount;
  if (totalSpentStat) totalSpentStat.textContent = formatINR(totalSpent);

  // Render trips with filter
  function renderTrips(filterStatus = "All") {
    const filtered = filterStatus === "All" 
      ? bookings 
      : bookings.filter(b => b.status === filterStatus);

    if (filtered.length === 0) {
      tripsContainer.innerHTML = `
        <div style="text-align: center; padding: 4.5rem 1rem; background: #fff; border-radius: 16px; border: 1px dashed #cbd5e1;">
          <i class="fa-solid fa-suitcase" style="font-size: 3.5rem; color: #94a3b8; margin-bottom: 1rem;"></i>
          <h3>No ${filterStatus !== 'All' ? filterStatus.toLowerCase() : ''} trips found</h3>
          <p class="text-muted" style="max-width: 450px; margin: 0 auto 1.5rem;">Ready to plan your next memorable vacation? Check out our curated packages!</p>
          <a href="packages.html" class="btn btn-primary"><i class="fa-solid fa-compass"></i> Discover Destinations</a>
        </div>
      `;
      return;
    }

    tripsContainer.innerHTML = filtered.map(trip => {
      const statusBadge = trip.status === "Upcoming"
        ? '<span class="badge badge-success"><i class="fa-regular fa-circle-check"></i> Confirmed & Upcoming</span>'
        : trip.status === "Completed"
        ? '<span class="badge badge-sky"><i class="fa-solid fa-flag-checkered"></i> Completed</span>'
        : '<span class="badge badge-danger"><i class="fa-solid fa-ban"></i> Cancelled</span>';

      return `
        <div class="trip-item-card">
          <img src="${trip.image}" alt="${trip.packageTitle}" class="trip-item-img" loading="lazy">
          
          <div class="trip-item-body">
            <div style="display: flex; align-items: center; justify-content: space-between; margin-bottom: 0.5rem;">
              <span style="font-family: monospace; font-size: 0.85rem; font-weight: 700; color: #0284c7; background: #e0f2fe; padding: 0.2rem 0.5rem; border-radius: 4px;">
                ${trip.id}
              </span>
              ${statusBadge}
            </div>

            <h3 style="font-size: 1.25rem; margin-bottom: 0.5rem; color: #0f172a;">${trip.packageTitle}</h3>
            
            <div style="display: flex; flex-wrap: wrap; gap: 1.5rem; font-size: 0.88rem; color: #475569; margin-bottom: 0.85rem;">
              <span><i class="fa-regular fa-calendar text-orange"></i> <strong>Travel:</strong> ${trip.travelDate} to ${trip.returnDate}</span>
              <span><i class="fa-solid fa-users text-sky"></i> <strong>Travelers:</strong> ${trip.travelers} Persons</span>
              <span><i class="fa-solid fa-credit-card text-success"></i> <strong>Payment:</strong> ${trip.paymentMethod}</span>
            </div>

            <div style="font-size: 0.82rem; color: #64748b;">
              Primary Passenger: <strong>${trip.guestName}</strong> (${trip.guestEmail})
            </div>
          </div>

          <div class="trip-item-actions">
            <div>
              <span style="font-size: 0.78rem; color: #64748b; display: block;">Total Paid</span>
              <strong style="font-size: 1.35rem; color: #ff6b35;">${formatINR(trip.totalAmount)}</strong>
            </div>

            <button class="btn btn-navy btn-sm" onclick="openVoucherModal('${trip.id}')">
              <i class="fa-solid fa-ticket"></i> E-Voucher
            </button>

            ${trip.status === "Upcoming" ? `
              <button class="btn btn-ghost btn-sm text-danger" onclick="cancelTripBooking('${trip.id}')">
                <i class="fa-solid fa-xmark"></i> Cancel Trip
              </button>
            ` : ''}
          </div>
        </div>
      `;
    }).join("");
  }

  // Filter tabs click
  const tripTabs = document.querySelectorAll(".trip-filter-tab");
  tripTabs.forEach(tab => {
    tab.addEventListener("click", () => {
      tripTabs.forEach(t => t.classList.remove("active"));
      tab.classList.add("active");
      renderTrips(tab.dataset.status);
    });
  });

  renderTrips("All");
}

async function cancelTripBooking(bookingId) {
  if (confirm(`Are you sure you want to cancel booking ${bookingId}? Free cancellation refund policy applies.`)) {
    // Notify Spring Boot Backend
    try {
      await fetch(`${API_BASE_URL}/bookings/${encodeURIComponent(bookingId)}/cancel`, {
        method: "POST"
      });
    } catch (err) {}

    const bookings = getBookings();
    const target = bookings.find(b => b.id === bookingId);
    if (target) {
      target.status = "Cancelled";
      localStorage.setItem("tripEaseBookings", JSON.stringify(bookings));
      showToast(`Trip ${bookingId} has been cancelled successfully. Refund initiated.`, "info");
      setupMyTripsPage();
    }
  }
}

function openVoucherModal(bookingId) {
  const bookings = getBookings();
  const trip = bookings.find(b => b.id === bookingId);
  if (!trip) return;

  const modal = document.getElementById("voucherModal");
  if (!modal) return;

  document.getElementById("vBookingId").textContent = trip.id;
  document.getElementById("vPkgTitle").textContent = trip.packageTitle;
  document.getElementById("vGuestName").textContent = trip.guestName;
  document.getElementById("vGuestPhone").textContent = trip.guestPhone;
  document.getElementById("vTravelDate").textContent = `${trip.travelDate} to ${trip.returnDate}`;
  document.getElementById("vTravelers").textContent = `${trip.travelers} Guests`;
  document.getElementById("vTotalPaid").textContent = formatINR(trip.totalAmount);
  document.getElementById("vStatusBadge").textContent = trip.status;

  modal.classList.add("active");
}

function printVoucher() {
  window.print();
}

// --- AUTHENTICATION (LOGIN & REGISTER) ---
function setupAuthPages() {
  // Password Visibility Toggle
  document.querySelectorAll(".password-toggle-btn").forEach(btn => {
    btn.addEventListener("click", () => {
      const input = btn.previousElementSibling;
      if (input && input.type === "password") {
        input.type = "text";
        btn.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
      } else if (input) {
        input.type = "password";
        btn.innerHTML = '<i class="fa-regular fa-eye"></i>';
      }
    });
  });

  // Login Form Submission
  const loginForm = document.getElementById("loginForm");
  if (loginForm) {
    // Forward redirect parameter to register link if present
    const redirectForReg = new URLSearchParams(window.location.search).get("redirect");
    if (redirectForReg) {
      document.querySelectorAll('a[href*="register.html"]').forEach(link => {
        link.href = `register.html?redirect=${encodeURIComponent(redirectForReg)}`;
      });
    }

    // If arriving from registration
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("registered")) {
      showToast("Account created successfully! Please log in.", "success");
    }

    loginForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const email = document.getElementById("loginEmail").value.trim();
      const pass = document.getElementById("loginPassword").value.trim();

      if (!email || !pass) {
        showToast("Please enter both email and password", "error");
        return;
      }

      try {
        const resp = await fetch(`${API_BASE_URL}/auth/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email: email, password: pass })
        });
        const result = await resp.json();
        if (resp.ok && result.success && result.data) {
          const user = {
            id: result.data.id,
            name: result.data.name,
            email: result.data.email,
            phone: result.data.phone || "+91 98765 43210",
            role: result.data.role
          };
          localStorage.setItem("tripEaseUser", JSON.stringify(user));
          showToast(`Welcome back, ${result.data.name}!`, "success");

          const targetUrl = getPostLoginRedirectUrl(user);
          setTimeout(() => window.location.href = targetUrl, 800);
          return;
        } else {
          showToast(result.message || "Invalid credentials", "error");
          return;
        }
      } catch (err) {
        // Fallback for offline testing
        const namePart = email.split("@")[0];
        const displayName = namePart.charAt(0).toUpperCase() + namePart.slice(1);
        const user = {
          name: displayName,
          email: email,
          phone: "+91 98765 43210"
        };
        localStorage.setItem("tripEaseUser", JSON.stringify(user));
        showToast(`Welcome back, ${displayName}!`, "success");

        const targetUrl = getPostLoginRedirectUrl(user);
        setTimeout(() => window.location.href = targetUrl, 800);
      }
    });
  }

  // Register Form Submission
  const registerForm = document.getElementById("registerForm");
  if (registerForm) {
    registerForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const name = document.getElementById("regName").value.trim();
      const email = document.getElementById("regEmail").value.trim();
      const phone = document.getElementById("regPhone").value.trim();
      const pass = document.getElementById("regPassword").value;
      const confirmPass = document.getElementById("regConfirmPassword").value;
      const terms = document.getElementById("regTerms");

      if (!name || !email || !phone || !pass) {
        showToast("Please complete all registration fields", "error");
        return;
      }

      if (pass !== confirmPass) {
        showToast("Passwords do not match. Please recheck.", "error");
        return;
      }

      if (terms && !terms.checked) {
        showToast("Please agree to the Terms of Service & Privacy Policy", "error");
        return;
      }

      try {
        const resp = await fetch(`${API_BASE_URL}/auth/register`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ name: name, email: email, phone: phone, password: pass })
        });
        const result = await resp.json();
        if (resp.ok && result.success && result.data) {
          const newUser = {
            id: result.data.id,
            name: result.data.name,
            email: result.data.email,
            phone: result.data.phone,
            role: result.data.role
          };
          localStorage.setItem("tripEaseUser", JSON.stringify(newUser));
          showToast(`Account created successfully! Welcome to TripEase, ${name}.`, "success");
          const params = new URLSearchParams(window.location.search);
          const redirect = params.get("redirect") ? decodeURIComponent(params.get("redirect")) : "index.html";
          setTimeout(() => window.location.href = redirect, 1000);
          return;
        } else {
          showToast(result.message || "Registration failed", "error");
          return;
        }
      } catch (err) {
        const newUser = { name, email, phone };
        localStorage.setItem("tripEaseUser", JSON.stringify(newUser));
        showToast(`Account created successfully! Welcome to TripEase, ${name}.`, "success");
        const params = new URLSearchParams(window.location.search);
        const redirect = params.get("redirect") ? decodeURIComponent(params.get("redirect")) : "index.html";
        setTimeout(() => window.location.href = redirect, 1000);
      }
    });
  }
}

// --- ADMIN DASHBOARD UI ---
function setupAdminDashboard() {
  const bookings = getBookings();
  const packages = getPackages();

  // Metrics
  let totalRev = 0;
  let activeTravelers = 0;

  bookings.forEach(b => {
    totalRev += Number(b.totalAmount) || 0;
    if (b.status === "Upcoming") activeTravelers += (b.travelers || 2);
  });

  const revEl = document.getElementById("adminTotalRevenue");
  const bookingsEl = document.getElementById("adminTotalBookings");
  const travelersEl = document.getElementById("adminActiveTravelers");
  const pkgsEl = document.getElementById("adminTotalPackages");

  if (revEl) revEl.textContent = formatINR(totalRev);
  if (bookingsEl) bookingsEl.textContent = bookings.length;
  if (travelersEl) travelersEl.textContent = activeTravelers;
  if (pkgsEl) pkgsEl.textContent = packages.length;

  // Render Admin Bookings Table
  function renderAdminTable(filterQuery = "") {
    const tableBody = document.getElementById("adminBookingsTableBody");
    if (!tableBody) return;

    let filtered = bookings;
    if (filterQuery) {
      const q = filterQuery.toLowerCase();
      filtered = bookings.filter(b => 
        b.id.toLowerCase().includes(q) ||
        b.guestName.toLowerCase().includes(q) ||
        b.packageTitle.toLowerCase().includes(q)
      );
    }

    tableBody.innerHTML = filtered.map(b => `
      <tr>
        <td><strong style="color: #0284c7; font-family: monospace;">${b.id}</strong></td>
        <td>
          <div style="font-weight: 700; color: #0f172a;">${b.guestName}</div>
          <div style="font-size: 0.78rem; color: #64748b;">${b.guestEmail}</div>
        </td>
        <td>
          <div style="font-weight: 600;">${b.packageTitle}</div>
          <div style="font-size: 0.78rem; color: #64748b;"><i class="fa-regular fa-calendar"></i> ${b.travelDate}</div>
        </td>
        <td><strong>${formatINR(b.totalAmount)}</strong></td>
        <td>
          <select onchange="updateBookingStatus('${b.id}', this.value)" style="padding: 0.35rem 0.6rem; border-radius: 6px; font-size: 0.82rem; font-weight: 600; border: 1.5px solid #cbd5e1; background: #fff;">
            <option value="Upcoming" ${b.status === 'Upcoming' ? 'selected' : ''}>Upcoming</option>
            <option value="Completed" ${b.status === 'Completed' ? 'selected' : ''}>Completed</option>
            <option value="Cancelled" ${b.status === 'Cancelled' ? 'selected' : ''}>Cancelled</option>
          </select>
        </td>
        <td>
          <button onclick="deleteBookingAdmin('${b.id}')" style="background: none; color: #ef4444; font-size: 0.95rem; padding: 0.3rem;" title="Delete Record">
            <i class="fa-solid fa-trash-can"></i>
          </button>
        </td>
      </tr>
    `).join("");
  }

  const searchInput = document.getElementById("adminSearchInput");
  if (searchInput) {
    searchInput.addEventListener("input", (e) => renderAdminTable(e.target.value));
  }

  renderAdminTable();

  // Add Package Form inside Admin Modal
  const addPkgForm = document.getElementById("addNewPackageForm");
  if (addPkgForm) {
    addPkgForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const title = document.getElementById("pkgNewTitle").value.trim();
      const dest = document.getElementById("pkgNewDestination").value.trim();
      const price = parseInt(document.getElementById("pkgNewPrice").value, 10);
      const category = document.getElementById("pkgNewCategory").value;
      const duration = document.getElementById("pkgNewDuration").value.trim();
      const img = document.getElementById("pkgNewImage").value.trim() || "https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80";

      if (!title || !dest || !price) {
        showToast("Please fill all required package details", "error");
        return;
      }

      const newPkg = {
        id: "pkg-" + (Date.now()),
        title: title,
        destination: dest,
        region: "North India",
        category: category,
        duration: duration || "5 Nights / 6 Days",
        daysCount: 6,
        nightsCount: 5,
        price: price,
        originalPrice: Math.round(price * 1.3),
        rating: 4.9,
        reviewsCount: 1,
        badge: "New Tour",
        featured: false,
        image: img,
        galleryJson: JSON.stringify([img]),
        overview: `Discover the breathtaking sights and unique experiences of ${dest} with TripEase exclusive tour.`,
        highlightsJson: JSON.stringify(["Expert Local Guide", "All Transfers in private AC vehicle", "Selected 4-star stays"]),
        inclusionsJson: JSON.stringify(["Accommodations", "Daily Breakfast", "Sightseeing"]),
        exclusionsJson: JSON.stringify(["Airfare", "Personal expenses"]),
        itineraryJson: JSON.stringify([
          { day: 1, title: "Arrival & Welcome", description: "Arrive at destination and transfer to hotel." },
          { day: 2, title: "City Sightseeing", description: "Full day sightseeing tour." }
        ])
      };

      try {
        await fetch(`${API_BASE_URL}/packages`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(newPkg)
        });
      } catch (err) {}

      const pkgs = getPackages();
      pkgs.unshift(newPkg);
      localStorage.setItem("tripEasePackages", JSON.stringify(pkgs));

      showToast(`Package "${title}" created successfully!`, "success");
      closeModal('addPackageModal');
      setupAdminDashboard();
    });
  }
}

async function updateBookingStatus(bookingId, newStatus) {
  try {
    await fetch(`${API_BASE_URL}/bookings/${encodeURIComponent(bookingId)}/status`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ status: newStatus })
    });
  } catch (err) {}

  const bookings = getBookings();
  const item = bookings.find(b => b.id === bookingId);
  if (item) {
    item.status = newStatus;
    localStorage.setItem("tripEaseBookings", JSON.stringify(bookings));
    showToast(`Booking ${bookingId} status updated to ${newStatus}`, "info");
  }
}

async function deleteBookingAdmin(bookingId) {
  if (confirm(`Are you sure you want to delete booking ${bookingId}?`)) {
    try {
      await fetch(`${API_BASE_URL}/bookings/${encodeURIComponent(bookingId)}`, {
        method: "DELETE"
      });
    } catch (err) {}

    let bookings = getBookings();
    bookings = bookings.filter(b => b.id !== bookingId);
    localStorage.setItem("tripEaseBookings", JSON.stringify(bookings));
    showToast(`Booking ${bookingId} removed`, "info");
    setupAdminDashboard();
  }
}

// --- MODAL UTILITIES ---
function openModal(id) {
  const modal = document.getElementById(id);
  if (modal) modal.classList.add("active");
}

function closeModal(id) {
  const modal = document.getElementById(id);
  if (modal) modal.classList.remove("active");
}

function openPhotoModal(imgUrl) {
  const modal = document.getElementById("photoLightboxModal");
  const img = document.getElementById("photoLightboxImg");
  if (modal && img) {
    img.src = imgUrl;
    modal.classList.add("active");
  }
}

// Close modals when clicking backdrop
document.addEventListener("click", (e) => {
  if (e.target.classList.contains("modal-backdrop")) {
    e.target.classList.remove("active");
  }
});

// Close modals with Escape key
document.addEventListener("keydown", (e) => {
  if (e.key === "Escape") {
    document.querySelectorAll(".modal-backdrop.active").forEach(m => m.classList.remove("active"));
  }
});

// --- SEARCH & FILTER (PACKAGES PAGE) ---
function setupPackagesFilterPage() {
  const allPackages = getPackages();
  const searchInput = document.getElementById("pkgSearchInput");
  const priceSlider = document.getElementById("priceSlider");
  const priceDisplay = document.getElementById("priceSliderValue");
  const sortSelect = document.getElementById("pkgSortSelect");

  // Read URL query parameter for pre-filtering (e.g. from home search or destinations page)
  const params = new URLSearchParams(window.location.search);
  const destQuery = params.get("destination") || "";
  const catQuery = params.get("category") || "";

  if (searchInput && destQuery) {
    searchInput.value = destQuery;
  }

  function applyFilters() {
    let filtered = [...allPackages];

    // 1. Keyword search (title or destination)
    const keyword = searchInput ? searchInput.value.trim().toLowerCase() : "";
    if (keyword) {
      filtered = filtered.filter(p => 
        p.title.toLowerCase().includes(keyword) || 
        p.destination.toLowerCase().includes(keyword)
      );
    }

    // 2. Category checkboxes / chips
    const activeCats = Array.from(document.querySelectorAll('.cat-filter-checkbox:checked')).map(cb => cb.value);
    if (activeCats.length > 0) {
      filtered = filtered.filter(p => activeCats.includes(p.category));
    }

    // 3. Price slider
    if (priceSlider) {
      const maxPrice = parseInt(priceSlider.value, 10);
      filtered = filtered.filter(p => p.price <= maxPrice);
    }

    // 4. Duration filters
    const activeDurations = Array.from(document.querySelectorAll('.duration-filter-checkbox:checked')).map(cb => cb.value);
    if (activeDurations.length > 0) {
      filtered = filtered.filter(p => {
        const days = p.daysCount || 5;
        return activeDurations.some(range => {
          if (range === "short") return days <= 4;
          if (range === "medium") return days >= 5 && days <= 7;
          if (range === "long") return days >= 8;
          return true;
        });
      });
    }

    // 5. Rating filter
    const activeRatings = Array.from(document.querySelectorAll('.rating-filter-checkbox:checked')).map(cb => parseFloat(cb.value));
    if (activeRatings.length > 0) {
      const minRating = Math.min(...activeRatings);
      filtered = filtered.filter(p => p.rating >= minRating);
    }

    // 6. Sorting
    if (sortSelect) {
      const sortVal = sortSelect.value;
      if (sortVal === "price-low") filtered.sort((a, b) => a.price - b.price);
      else if (sortVal === "price-high") filtered.sort((a, b) => b.price - a.price);
      else if (sortVal === "rating") filtered.sort((a, b) => b.rating - a.rating);
      else if (sortVal === "popular") filtered.sort((a, b) => b.reviewsCount - a.reviewsCount);
    }

    // Update count display
    const countEl = document.getElementById("packagesFoundCount");
    if (countEl) countEl.textContent = filtered.length;

    renderPackagesGrid(filtered, "packagesContainer");
  }

  // Event Listeners
  if (searchInput) searchInput.addEventListener("input", applyFilters);
  if (priceSlider && priceDisplay) {
    priceSlider.addEventListener("input", (e) => {
      priceDisplay.textContent = formatINR(e.target.value);
      applyFilters();
    });
  }
  if (sortSelect) sortSelect.addEventListener("change", applyFilters);

  document.querySelectorAll(".cat-filter-checkbox, .duration-filter-checkbox, .rating-filter-checkbox").forEach(cb => {
    cb.addEventListener("change", applyFilters);
  });

  // If category was passed in URL
  if (catQuery) {
    const cb = document.querySelector(`.cat-filter-checkbox[value="${catQuery}"]`);
    if (cb) cb.checked = true;
  }

  window.resetPackageFilters = function() {
    if (searchInput) searchInput.value = "";
    if (priceSlider) {
      priceSlider.value = 150000;
      if (priceDisplay) priceDisplay.textContent = formatINR(150000);
    }
    document.querySelectorAll(".cat-filter-checkbox, .duration-filter-checkbox, .rating-filter-checkbox").forEach(cb => {
      cb.checked = false;
    });
    if (sortSelect) sortSelect.value = "popular";
    applyFilters();
  };

  applyFilters();
}

// --- NEWSLETTER FORM ---
function setupNewsletter() {
  document.querySelectorAll(".newsletter-form").forEach(form => {
    form.addEventListener("submit", (e) => {
      e.preventDefault();
      const input = form.querySelector("input[type='email']");
      if (input && input.value) {
        showToast("Thank you for subscribing! Check your inbox for exclusive travel deals.", "success");
        input.value = "";
      }
    });
  });
}

// --- DOM READY BOOTSTRAP ---
document.addEventListener("DOMContentLoaded", () => {
  initStorage();
  setupNavbar();
  setupNewsletter();

  // Page Specific Inits
  if (document.getElementById("homeDestinationsGrid")) {
    renderDestinationsGrid(TRIPEASE_DATA.destinations.slice(0, 6), "homeDestinationsGrid");
  }
  if (document.getElementById("homePackagesGrid")) {
    const pkgs = getPackages().filter(p => p.featured);
    renderPackagesGrid(pkgs.length ? pkgs : getPackages().slice(0, 6), "homePackagesGrid");
  }

  if (document.getElementById("allDestinationsGrid")) {
    renderDestinationsGrid(TRIPEASE_DATA.destinations, "allDestinationsGrid");
    // Destinations search & filter
    const destSearch = document.getElementById("destSearchInput");
    const destPills = document.querySelectorAll(".dest-category-pill");
    
    function filterDestinations() {
      const q = destSearch ? destSearch.value.trim().toLowerCase() : "";
      const activePill = document.querySelector(".dest-category-pill.active");
      const region = activePill ? activePill.dataset.region : "all";

      let filtered = TRIPEASE_DATA.destinations;
      if (region && region !== "all") {
        filtered = filtered.filter(d => d.region === region || d.vibe.includes(region));
      }
      if (q) {
        filtered = filtered.filter(d => 
          d.name.toLowerCase().includes(q) || 
          d.country.toLowerCase().includes(q) ||
          d.description.toLowerCase().includes(q)
        );
      }
      renderDestinationsGrid(filtered, "allDestinationsGrid");
    }

    if (destSearch) destSearch.addEventListener("input", filterDestinations);
    destPills.forEach(pill => {
      pill.addEventListener("click", () => {
        destPills.forEach(p => p.classList.remove("active"));
        pill.classList.add("active");
        filterDestinations();
      });
    });
  }

  if (document.getElementById("packagesContainer")) {
    setupPackagesFilterPage();
  }

  if (document.getElementById("detailsTitle")) {
    setupPackageDetailsPage();
  }

  if (document.getElementById("bookingMasterForm")) {
    setupBookingPage();
  }

  if (document.getElementById("myTripsList")) {
    setupMyTripsPage();
  }

  if (document.getElementById("loginForm") || document.getElementById("registerForm")) {
    setupAuthPages();
  }

  if (document.getElementById("adminBookingsTableBody")) {
    setupAdminDashboard();
  }
});
