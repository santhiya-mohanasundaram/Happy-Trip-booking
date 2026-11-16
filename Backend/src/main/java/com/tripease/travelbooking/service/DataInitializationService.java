package com.tripease.travelbooking.service;

import com.tripease.travelbooking.entity.*;
import com.tripease.travelbooking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;
    private final TravelPackageRepository packageRepository;
    private final HotelRepository hotelRepository;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CouponRepository couponRepository;
    private final TicketService ticketService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializationService(UserRepository userRepository,
                                     DestinationRepository destinationRepository,
                                     TravelPackageRepository packageRepository,
                                     HotelRepository hotelRepository,
                                     FlightRepository flightRepository,
                                     BookingRepository bookingRepository,
                                     PaymentRepository paymentRepository,
                                     CouponRepository couponRepository,
                                     TicketService ticketService,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
        this.packageRepository = packageRepository;
        this.hotelRepository = hotelRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.couponRepository = couponRepository;
        this.ticketService = ticketService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedCoupons();
        seedDestinations();
        seedPackages();
        seedHotels();
        seedFlights();
        seedBookings();
    }

    private void seedUsers() {
        // Ensure Admin user exists with exact demo credentials: admin / Admin@123
        if (!userRepository.existsByUsername("admin") && !userRepository.existsByEmail("admin@tripease.com")) {
            User admin = new User(
                    "Administrator",
                    "admin",
                    "admin@tripease.com",
                    "+91 98765 00000",
                    passwordEncoder.encode("Admin@123"),
                    "ADMIN"
            );
            userRepository.save(admin);
        } else if (userRepository.existsByEmail("admin@tripease.com")) {
            userRepository.findByEmail("admin@tripease.com").ifPresent(admin -> {
                admin.setUsername("admin");
                admin.setRole("ADMIN");
                admin.setPassword(passwordEncoder.encode("Admin@123"));
                userRepository.save(admin);
            });
        }

        // Ensure Demo User exists
        if (!userRepository.existsByEmail("arjun.sharma@example.com")) {
            User user = new User(
                    "Arjun Sharma",
                    "arjun_sharma",
                    "arjun.sharma@example.com",
                    "+91 98765 43210",
                    passwordEncoder.encode("tripease123"),
                    "USER"
            );
            userRepository.save(user);
        } else {
            userRepository.findByEmail("arjun.sharma@example.com").ifPresent(u -> {
                if (u.getUsername() == null || u.getUsername().isBlank()) {
                    u.setUsername("arjun_sharma");
                    userRepository.save(u);
                }
            });
        }
    }

    private void seedCoupons() {
        if (couponRepository.count() == 0) {
            couponRepository.save(new Coupon("TRAVEL10", 10.0, 10000.0, true));
            couponRepository.save(new Coupon("TRIPEASE2024", 15.0, 5000.0, true));
            couponRepository.save(new Coupon("WANDERLUST", 10.0, 8000.0, true));
        }
    }

    private void seedDestinations() {
        if (destinationRepository.count() == 0 || destinationRepository.findById("kashmir").map(d -> d.getLocation() == null).orElse(true)) {
            Destination kashmir = new Destination("kashmir", "Kashmir", "India", "North India", "Heaven on Earth", "Snow & Mountains", 24999.0, 14,
                    "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=800&q=80",
                    "Pristine valleys, serene Shikara rides on Dal Lake, and snow-kissed Himalayan slopes in Gulmarg & Pahalgam.",
                    "March to October & Dec-Feb for Snow", "4°C - 22°C");
            kashmir.setLocation("Jammu & Kashmir, Northern India");
            kashmir.setNearestAirport("Sheikh ul-Alam International Airport (SXR), Srinagar (12 km from city center)");
            kashmir.setNearestRailway("Jammu Tawi Railway Station (JAT - 290 km) & Udhampur Railway Station");
            kashmir.setHowToReach("Direct flights connect Srinagar to Delhi, Mumbai, and Bengaluru. Regular deluxe cabs and J&K RTC buses ply from Jammu to Srinagar.");
            kashmir.setPopularAttractions("Dal Lake, Gulmarg Gondola Cable Car, Betaab Valley, Shalimar Bagh, Nishat Bagh, Sonmarg Thajiwas Glacier");
            kashmir.setFamousPlaces("Srinagar, Gulmarg, Pahalgam, Sonmarg, Doodhpathri, Yusmarg");
            kashmir.setLocalFood("Kashmiri Wazwan (Rogan Josh, Gustaba, Rista), Yakhni, Dum Aloo, Saffron Kahwa tea, Sheermal & Bakerkhani breads");
            kashmir.setAccommodationOptions("Luxury carved Dal Lake houseboats, boutique valley-view resorts in Gulmarg, riverside cottages in Pahalgam, heritage hotels in Srinagar");
            kashmir.setLocalTransport("Private tourist taxis (sedan & SUV), auto-rickshaws, traditional Shikara boats on Dal Lake, pony rides on mountain paths");
            kashmir.setTravelTips("Carry valid government photo IDs for checkpoints. Pre-book Gondola Phase 1 & 2 online. Pack thermal innerwear even in early spring.");
            kashmir.setEstimatedBudget("₹25,000 - ₹45,000 per couple (5-6 days including stay, transport, meals, and sightseeing)");
            kashmir.setRecommendedDuration("5 Nights / 6 Days");
            kashmir.setActivities("Dal Lake Sunset Shikara ride, Gulmarg skiing and snowboarding, pony riding in Baisaran, trout fishing in Lidder River, shopping authentic Pashmina & saffron");
            destinationRepository.save(kashmir);

            Destination kerala = new Destination("kerala", "Kerala", "India", "South India", "God's Own Country", "Tropical & Backwaters", 19999.0, 18,
                    "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=800&q=80",
                    "Tranquil backwater houseboats in Alleppey, emerald tea plantations in Munnar, and Ayurvedic wellness retreats.",
                    "September to March", "22°C - 30°C");
            kerala.setLocation("Southwest Malabar Coast, India");
            kerala.setNearestAirport("Cochin International Airport (COK), Trivandrum (TRV), Calicut (CCJ)");
            kerala.setNearestRailway("Ernakulam Junction (ERS), Alappuzha (ALLP), Kottayam (KTYM)");
            kerala.setHowToReach("Well connected by rail and international flights to Cochin. NH 66 and NH 544 provide seamless intercity highway connectivity.");
            kerala.setPopularAttractions("Alleppey Backwaters, Eravikulam National Park, Periyar Wildlife Lake, Mattupetty Dam, Athirappilly Waterfalls");
            kerala.setFamousPlaces("Munnar, Alleppey, Thekkady, Wayanad, Kovalam, Varkala Cliff");
            kerala.setLocalFood("Kerala Traditional Sadhya served on banana leaf, Appam with Stew, Karimeen Pollichathu, Malabar Parotta, Puttu & Kadala Curry");
            kerala.setAccommodationOptions("Traditional thatched Kettuvallam houseboats, tea estate luxury chalets, spice plantation treehouses, coastal Ayurvedic retreats");
            kerala.setLocalTransport("Private tourist cabs, KSRTC AC buses, auto-rickshaws, state government ferry boats through backwater canals");
            kerala.setTravelTips("Book houseboats with certified green palm ratings. Attend an evening Kathakali and Kalaripayattu performance in Thekkady.");
            kerala.setEstimatedBudget("₹20,000 - ₹38,000 per couple (4-5 days package)");
            kerala.setRecommendedDuration("4 Nights / 5 Days");
            kerala.setActivities("Overnight backwater cruise, tea factory and spice garden walking tours, elephant sanctuary visits, bamboo rafting in Periyar");
            destinationRepository.save(kerala);

            Destination goa = new Destination("goa", "Goa", "India", "West India", "Sun, Sand & Sea", "Beaches", 14499.0, 22,
                    "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=800&q=80",
                    "Golden palm-fringed beaches, Portuguese heritage churches, vibrant sunset shacks, and exhilarating water sports.",
                    "October to April", "24°C - 32°C");
            goa.setLocation("Konkan Coast, Western India");
            goa.setNearestAirport("Manohar International Airport Mopa (GOX) & Dabolim Airport (GOI)");
            goa.setNearestRailway("Madgaon Junction (MAO) & Thivim Railway Station (THVM)");
            goa.setHowToReach("Dozens of daily flights from all metro airports in India. Overnight luxury sleeper buses connect Goa from Mumbai, Pune, and Bengaluru.");
            goa.setPopularAttractions("Basilica of Bom Jesus, Fort Aguada, Chapora Fort, Dudhsagar Waterfalls, Baga Beach, Palolem Beach");
            goa.setFamousPlaces("North Goa (Baga, Calangute, Anjuna, Vagator), South Goa (Colva, Benaulim, Palolem, Agonda)");
            goa.setLocalFood("Goan Fish Curry Thali, Prawn Balchão, Chicken Xacuti, Pork Vindaloo, Bebinca layered dessert, fresh coconut water");
            goa.setAccommodationOptions("5-star beachside luxury resorts, private pool Portuguese villas, cozy beachfront wooden cottages, boutique heritage hotels");
            goa.setLocalTransport("Self-drive rental cars and scooters, pilot motorcycle taxis, app-based GoaMiles cabs, private hotel transfers");
            goa.setTravelTips("Rent a scooter for North Goa exploration. Carry sunscreen and hats. Respect heritage cathedral dress codes.");
            goa.setEstimatedBudget("₹15,000 - ₹30,000 per couple (3-4 days)");
            goa.setRecommendedDuration("3 Nights / 4 Days");
            goa.setActivities("Scuba diving at Grand Island, parasailing, jet skiing, Mandovi River luxury sunset catamaran cruise, dolphin watching");
            destinationRepository.save(goa);

            Destination ladakh = new Destination("ladakh", "Ladakh", "India", "North India", "Land of High Passes", "Snow & Mountains", 32999.0, 9,
                    "https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=800&q=80",
                    "Mesmerizing blue waters of Pangong Tso, high mountain passes of Khardung La, and ancient Buddhist monasteries.",
                    "May to September", "8°C - 20°C");
            ladakh.setLocation("Trans-Himalayan Region, Northern India");
            ladakh.setNearestAirport("Kushok Bakula Rimpochee Airport (IXL), Leh (3,256m altitude)");
            ladakh.setNearestRailway("Jammu Tawi (700 km); Jammu-Baramulla rail link nearing completion");
            ladakh.setHowToReach("Direct flights operate from Delhi, Mumbai, and Chandigarh to Leh. Scenic road routes: Manali-Leh Highway and Srinagar-Leh Highway.");
            ladakh.setPopularAttractions("Pangong Tso Lake, Khardung La Pass (17,582 ft), Nubra Valley Sand Dunes, Magnetic Hill, Thiksey Monastery, Shanti Stupa");
            ladakh.setFamousPlaces("Leh, Nubra Valley (Diskit & Hunder), Pangong Lake, Tso Moriri, Turtuk (Balti border village)");
            ladakh.setLocalFood("Thukpa noodle soup, steamed Momos, Tingmo Tibetan bread, Butter Tea (Gur Gur Chai), Skyu stew, fresh Apricot jam");
            ladakh.setAccommodationOptions("Eco-luxury glamping Swiss tents by Pangong Lake, luxury boutique hotels in Leh, apple orchard homestays in Nubra");
            ladakh.setLocalTransport("Dedicated 4x4 SUVs (Innova, Scorpio, Fortuner) authorized by Leh Taxi Union; motorbikes (Royal Enfield) on rent");
            ladakh.setTravelTips("Mandatory 24-48 hours rest upon arrival in Leh for high-altitude acclimatization. Drink plenty of water and keep Diamox handy.");
            ladakh.setEstimatedBudget("₹35,000 - ₹60,000 per person (6-7 days)");
            ladakh.setRecommendedDuration("6 Nights / 7 Days");
            ladakh.setActivities("Double-humped Bactrian camel safari in Hunder dunes, stargazing under zero light pollution skies at Pangong, river rafting in Zanskar");
            destinationRepository.save(ladakh);

            Destination rajasthan = new Destination("rajasthan", "Rajasthan", "India", "North India", "Royal Heritage", "Heritage & Palaces", 26499.0, 16,
                    "https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=800&q=80",
                    "Opulent palaces of Udaipur, towering golden forts of Jaisalmer, pink architecture of Jaipur, and desert camel safaris.",
                    "October to March", "14°C - 28°C");
            rajasthan.setLocation("Northwest India");
            rajasthan.setNearestAirport("Jaipur International Airport (JAI), Udaipur (UDR), Jodhpur (JDH)");
            rajasthan.setNearestRailway("Jaipur Junction (JP), Udaipur City (UDZ), Jodhpur Junction (JU)");
            rajasthan.setHowToReach("High-speed Vande Bharat trains connect Delhi to Jaipur/Ajmer. Express highways and daily domestic flights provide great connectivity.");
            rajasthan.setPopularAttractions("Amber Fort Jaipur, Hawa Mahal, Lake Palace Udaipur, Mehrangarh Fort Jodhpur, Jaisalmer Golden Sand Dunes");
            rajasthan.setFamousPlaces("Jaipur, Udaipur, Jodhpur, Jaisalmer, Pushkar, Mount Abu");
            rajasthan.setLocalFood("Dal Baati Churma, Ker Sangri, Laal Maas, Gatte ki Sabzi, Pyaaz Kachori, Ghevar and Mawa Kachori sweets");
            rajasthan.setAccommodationOptions("Authentic royal heritage Havelis, luxury palace hotels managed by Taj/Oberoi, desert luxury Swiss tents in Sam dunes");
            rajasthan.setLocalTransport("Private AC tourist cabs, auto-rickshaws, pre-booked intercity heritage buses");
            rajasthan.setTravelTips("Wear comfortable walking shoes for palace and fort tours. Bargain politely at local handicraft bazaars for block prints and blue pottery.");
            rajasthan.setEstimatedBudget("₹26,000 - ₹50,000 per couple (5-6 days)");
            rajasthan.setRecommendedDuration("5 Nights / 6 Days");
            rajasthan.setActivities("Boat ride on Lake Pichola at sunset, camel and jeep safari in Thar Desert, puppet shows and Rajasthani folk dances, zip-lining at Mehrangarh");
            destinationRepository.save(rajasthan);

            Destination himachal = new Destination("himachal", "Himachal Pradesh", "India", "North India", "Mountain Paradise", "Snow & Mountains", 16999.0, 20,
                    "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?auto=format&fit=crop&w=800&q=80",
                    "Snowy adventures in Solang Valley, picturesque pines of Manali, colonial charm of Shimla, and cozy mountain cafes.",
                    "All Year Round (Dec-Feb for Snow, April-June for Pleasant Weather)", "10°C - 22°C");
            himachal.setLocation("Western Himalayas, India");
            himachal.setNearestAirport("Bhuntar Airport Kullu (KUU), Chandigarh International Airport (IXC), Jubbarhatti Shimla (SLV)");
            himachal.setNearestRailway("Kalka Railway Station (KLK) with UNESCO World Heritage Kalka-Shimla Toy Train");
            himachal.setHowToReach("Overnight Volvo AC sleeper buses operate daily from Delhi (Kashmere Gate) to Manali and Shimla.");
            himachal.setPopularAttractions("Atal Tunnel Rohtang, Solang Valley, Mall Road Shimla, Jakhoo Temple, Hadimba Temple Manali, Kasol & Parvati Valley");
            himachal.setFamousPlaces("Manali, Shimla, Dharamshala/McLeod Ganj, Spiti Valley, Kasol, Dalhousie");
            himachal.setLocalFood("Himachali Dham feast, Siddu with ghee, Madra, Trout Fish, Chha Gosht, fresh orchard apples, rhododendron juice");
            himachal.setAccommodationOptions("Riverside cedar cottages in Old Manali, luxury mountain chalets with balcony views, boutique pine stays in Shimla");
            himachal.setLocalTransport("Private tourist cabs, local HRTC buses, auto-rickshaws");
            himachal.setTravelTips("Carry valid ID for Atal Tunnel checkposts. Pre-book Rohtang Pass permits in peak summer.");
            himachal.setEstimatedBudget("₹18,000 - ₹35,000 per couple (4-5 days)");
            himachal.setRecommendedDuration("4 Nights / 5 Days");
            himachal.setActivities("Paragliding in Solang, river rafting in Beas, trekking to Jogini Waterfall, riding the UNESCO Kalka-Shimla Toy Train");
            destinationRepository.save(himachal);
        }
    }

    private void seedPackages() {
        if (packageRepository.count() == 0 || packageRepository.findById("pkg-1").map(p -> p.getFoodDetails() == null).orElse(true)) {
            TravelPackage pkg1 = new TravelPackage();
            pkg1.setId("pkg-1");
            pkg1.setTitle("Mesmerizing Kashmir & Dal Lake Paradise");
            pkg1.setDestination("Kashmir, India");
            pkg1.setRegion("North India");
            pkg1.setCategory("Honeymoon Packages");
            pkg1.setDuration("5 Nights / 6 Days");
            pkg1.setDaysCount(6);
            pkg1.setNightsCount(5);
            pkg1.setPrice(24999.0);
            pkg1.setOriginalPrice(34999.0);
            pkg1.setRating(4.9);
            pkg1.setReviewsCount(148);
            pkg1.setLikesCount(2);
            pkg1.setBadge("Bestseller");
            pkg1.setFeatured(true);
            pkg1.setImage("https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80");
            pkg1.setOverview("Experience the timeless enchantment of Kashmir, celebrated as paradise on earth. Stay in a luxurious carved wooden houseboat on Dal Lake, glide through floating flower markets on a private Shikara, ride the Gulmarg Gondola to snow peaks, and wander along the Lidder river in Pahalgam.");
            pkg1.setFoodDetails("Daily Morning Buffet Breakfast & Chef-curated 4-Course Dinners featuring authentic Kashmiri Wazwan (Rogan Josh, Gustaba, Dum Aloo) & unlimited aromatic Kahwa tea.");
            pkg1.setTransportation("Private AC Sedan for all transfers, sightseeing, and airport pick-and-drop. Complementary 1-Hour Sunset Shikara ride on Dal Lake.");
            pkg1.setPlacesCovered("Srinagar, Dal Lake, Gulmarg, Kongdoori Snow Point, Pahalgam, Betaab Valley, Aru Valley, Avantipur Ruins");
            pkg1.setTermsAndConditions("Free cancellation up to 72 hours before travel. Gondola tickets are subject to weather permits. Government photo ID mandatory at check-in.");
            pkg1.setHighlightsJson("[\"Traditional Deluxe Houseboat stay on Dal Lake with evening Shikara ride\",\"Gondola Cable Car Ride in Gulmarg up to Phase 1 snow point\",\"Picturesque drive through saffron fields and Apple Orchards in Pahalgam\",\"Visits to Mughal Gardens: Shalimar Bagh, Nishat Bagh & Chashme Shahi\",\"Authentic Kashmiri Wazwan dinner experience and Kahwa tea tasting\"]");
            pkg1.setInclusionsJson("[\"5 Nights Deluxe 4-Star Accommodation (4N Hotel + 1N Houseboat)\",\"Daily Morning Buffet Breakfast & Chef-curated Dinners\",\"Private AC Sedan for all transfers, sightseeing, and airport pick-and-drop\",\"1-Hour Complementary Sunset Shikara Ride on Dal Lake\",\"All toll taxes, parking fees, driver allowance & inner line permits\",\"24/7 Dedicated Trip Concierge & Local Guide Assistance\"]");
            pkg1.setExclusionsJson("[\"Airfare / Train tickets to and from Srinagar\",\"Gondola Phase 2 tickets (can be added on request)\",\"Pony rides, sledging, or snow scooter activities in Gulmarg/Pahalgam\",\"Personal expenses, laundry, telephone calls, and camera charges\"]");
            pkg1.setStaysJson("[{\"name\":\"The Grand Mamta / Radisson Srinagar\",\"rating\":\"4.8★ Luxury\"},{\"name\":\"Royal Heritage Houseboat, Dal Lake\",\"rating\":\"4.9★ Heritage\"},{\"name\":\"Kolahoi Green Resort, Pahalgam\",\"rating\":\"4.7★ Scenic Resort\"}]");
            pkg1.setItineraryJson("[{\"day\":1,\"title\":\"Arrival in Srinagar & Sunset Shikara Ride\",\"description\":\"Arrive at Srinagar Airport, check into houseboat and enjoy sunset Shikara on Dal Lake.\"},{\"day\":2,\"title\":\"Gulmarg Meadow of Flowers & Gondola\",\"description\":\"Day trip to Gulmarg with gondola cable car ride.\"},{\"day\":3,\"title\":\"Pahalgam Valley of Shepherds\",\"description\":\"Drive through saffron fields to riverside resort in Pahalgam.\"},{\"day\":4,\"title\":\"Mughal Gardens Srinagar\",\"description\":\"Explore Shalimar and Nishat Mughal gardens.\"},{\"day\":5,\"title\":\"Sonmarg Meadow of Gold\",\"description\":\"Scenic excursion to Thajiwas Glacier.\"},{\"day\":6,\"title\":\"Airport Departure\",\"description\":\"Transfer to Srinagar Airport for onward journey.\"}]");
            pkg1.setGalleryJson("[\"https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80\",\"https://images.unsplash.com/photo-1566837945700-30057527ade0?auto=format&fit=crop&w=1000&q=80\",\"https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg1);

            TravelPackage pkg2 = new TravelPackage();
            pkg2.setId("pkg-2");
            pkg2.setTitle("Enchanting Kerala Backwaters & Munnar Hills");
            pkg2.setDestination("Kerala, India");
            pkg2.setRegion("South India");
            pkg2.setCategory("Family Holiday Packages");
            pkg2.setDuration("4 Nights / 5 Days");
            pkg2.setDaysCount(5);
            pkg2.setNightsCount(4);
            pkg2.setPrice(19999.0);
            pkg2.setOriginalPrice(27999.0);
            pkg2.setRating(4.8);
            pkg2.setReviewsCount(210);
            pkg2.setLikesCount(2);
            pkg2.setBadge("Trending");
            pkg2.setFeatured(true);
            pkg2.setImage("https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=1000&q=80");
            pkg2.setOverview("Unwind in God's Own Country. Traverse mist-cloaked tea estates in Munnar, witness endangered Nilgiri Tahr at Eravikulam, visit spice plantations in Thekkady, and cruise the backwaters of Alleppey.");
            pkg2.setFoodDetails("Daily Breakfast at all hotels + All Meals on Houseboat (Traditional Kerala Sadhya Lunch, Evening Tea & Snacks, Dinner, and Breakfast prepared fresh on board).");
            pkg2.setTransportation("Private AC Vehicle with professional chauffeur for the entire journey from Cochin pickup to drop.");
            pkg2.setPlacesCovered("Cochin, Cheeyappara Waterfalls, Munnar, Mattupetty, Thekkady, Periyar Wildlife Sanctuary, Alleppey Backwaters");
            pkg2.setTermsAndConditions("Houseboat AC operates from 9:00 PM to 6:00 AM in standard deluxe category. Safe cancellation policy applies up to 3 days prior.");
            pkg2.setHighlightsJson("[\"Private AC Deluxe Houseboat overnight cruise in Alleppey backwaters\",\"Tea tasting and plantation tour in Munnar's rolling hills\",\"Visit Cheeyappara & Valara cascading waterfalls\",\"Periyar Wildlife Sanctuary boat safari in Thekkady\",\"Traditional Kerala Sadhya lunch served on banana leaf\"]");
            pkg2.setInclusionsJson("[\"4 Nights Handpicked 4-Star Accommodations (2N Munnar, 1N Thekkady, 1N Houseboat)\",\"Daily Breakfast at all hotels + All Meals on Houseboat\",\"Private AC Vehicle with professional chauffeur for the entire trip\",\"Guided Spice Plantation tour & Tea Factory visit\"]");
            pkg2.setExclusionsJson("[\"Flight/Train tickets to Cochin\",\"Entry fees at monuments and parks\",\"Ayurvedic spa treatments\"]");
            pkg2.setStaysJson("[{\"name\":\"Amber Dale Luxury Resort, Munnar\",\"rating\":\"4.8★ Hill Resort\"},{\"name\":\"Rainbow Cruises Houseboat, Alleppey\",\"rating\":\"4.9★ Backwater Haven\"}]");
            pkg2.setItineraryJson("[{\"day\":1,\"title\":\"Cochin to Munnar\",\"description\":\"Drive through Cheeyappara waterfalls to Munnar resort.\"},{\"day\":2,\"title\":\"Munnar Tea Country\",\"description\":\"Eravikulam National Park and Mattupetty Dam.\"},{\"day\":3,\"title\":\"Thekkady Spice Wonderland\",\"description\":\"Spice plantation tour and Periyar wildlife lake.\"},{\"day\":4,\"title\":\"Alleppey Houseboat\",\"description\":\"Check-in to private deluxe houseboat with chef-prepared meals.\"},{\"day\":5,\"title\":\"Departure Cochin\",\"description\":\"Disembark and transfer to Cochin Airport.\"}]");
            pkg2.setGalleryJson("[\"https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=1000&q=80\",\"https://images.unsplash.com/photo-1593693397690-362cb9666fc2?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg2);

            TravelPackage pkg3 = new TravelPackage();
            pkg3.setId("pkg-3");
            pkg3.setTitle("Goa Beach Retreat & Sunset Catamaran Cruise");
            pkg3.setDestination("Goa, India");
            pkg3.setRegion("West India");
            pkg3.setCategory("Beach Packages");
            pkg3.setDuration("3 Nights / 4 Days");
            pkg3.setDaysCount(4);
            pkg3.setNightsCount(3);
            pkg3.setPrice(14499.0);
            pkg3.setOriginalPrice(21999.0);
            pkg3.setRating(4.7);
            pkg3.setReviewsCount(320);
            pkg3.setLikesCount(2);
            pkg3.setBadge("Popular Deal");
            pkg3.setFeatured(true);
            pkg3.setImage("https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80");
            pkg3.setOverview("Sun, golden sands, lively shacks, and rich Portuguese colonial history. Relax by your resort pool, discover historic churches in Old Goa, and set sail on an energetic sunset cruise on the Mandovi River.");
            pkg3.setFoodDetails("Daily Multi-Cuisine Breakfast Buffet included. Exclusive 15% discount voucher for top beach shacks in Baga and Calangute.");
            pkg3.setTransportation("Airport/Railway station private AC transfers. 2 full days private AC vehicle sightseeing in North & South Goa.");
            pkg3.setPlacesCovered("Panaji, Baga, Calangute, Anjuna, Aguada Fort, Chapora Fort, Basilica of Bom Jesus, Mandovi River");
            pkg3.setTermsAndConditions("Complimentary cruise tickets valid on day 3 evening. Water sports subject to sea conditions.");
            pkg3.setHighlightsJson("[\"Stay at a 4-Star Beachside Resort with swimming pool and buffet breakfast\",\"Mandovi River Sunset Cruise with Goan folk music and DJ\",\"North Goa Beach Tour: Baga, Calangute, Anjuna & Chapora Fort\"]");
            pkg3.setInclusionsJson("[\"3 Nights accommodation at premium beach resort\",\"Daily Breakfast Buffet\",\"Goa Airport / Railway station pickup & drop\",\"Complimentary Mandovi River cruise tickets\"]");
            pkg3.setExclusionsJson("[\"Flights to/from Goa\",\"Water sports fees\",\"Personal bar and nightclub expenses\"]");
            pkg3.setStaysJson("[{\"name\":\"Acron Waterfront Resort / Fairfield Marriott\",\"rating\":\"4.7★ Beachside\"}]");
            pkg3.setItineraryJson("[{\"day\":1,\"title\":\"Arrival Goa\",\"description\":\"Airport transfer to beach resort.\"},{\"day\":2,\"title\":\"North Goa Beaches\",\"description\":\"Baga, Calangute and Chapora Fort.\"},{\"day\":3,\"title\":\"Old Goa & Sunset Cruise\",\"description\":\"Basilica of Bom Jesus and Mandovi river cruise.\"},{\"day\":4,\"title\":\"Departure\",\"description\":\"Cab transfer to airport.\"}]");
            pkg3.setGalleryJson("[\"https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg3);
        }
    }

    private void seedHotels() {
        if (hotelRepository.count() == 0) {
            hotelRepository.saveAll(List.of(
                    new Hotel("The Grand Mamta Srinagar", "Kashmir", "Srinagar", 4.8, 6500.0, "https://images.unsplash.com/photo-1566837945700-30057527ade0?auto=format&fit=crop&w=800&q=80", "Deluxe Room", "Free WiFi, Mountain View Balcony, Heating, Restaurant", true),
                    new Hotel("Amber Dale Luxury Resort", "Kerala", "Munnar", 4.8, 5500.0, "https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?auto=format&fit=crop&w=800&q=80", "Valley View Suite", "Tea Garden View, Spa, Free Breakfast, Multi-Cuisine Diner", true),
                    new Hotel("Acron Waterfront Resort", "Goa", "Baga", 4.7, 4800.0, "https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?auto=format&fit=crop&w=800&q=80", "Poolside Villa Room", "Swimming Pool, Beach Access, Riverfront Bar, Free WiFi", true)
            ));
        }
    }

    private void seedFlights() {
        if (flightRepository.count() == 0) {
            flightRepository.saveAll(List.of(
                    new Flight("6E-2041", "IndiGo", "Delhi", "Srinagar", "06:15 AM", "07:45 AM", 4800.0, "Economy", 45),
                    new Flight("AI-852", "Air India", "Mumbai", "Srinagar", "08:30 AM", "11:15 AM", 6200.0, "Economy", 32),
                    new Flight("6E-512", "IndiGo", "Bengaluru", "Cochin", "07:00 AM", "08:10 AM", 3200.0, "Economy", 50)
            ));
        }
    }

    private void seedBookings() {
        if (bookingRepository.count() == 0) {
            Booking b1 = new Booking();
            b1.setId("TE-2026-8941");
            b1.setPackageId("pkg-1");
            b1.setBookingType("PACKAGE");
            b1.setPackageTitle("Mesmerizing Kashmir & Dal Lake Paradise");
            b1.setDestination("Kashmir, India");
            b1.setImage("https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=600&q=80");
            b1.setTravelDate("2026-11-28");
            b1.setReturnDate("2026-12-03");
            b1.setTravelers(2);
            b1.setGuestName("Arjun Sharma");
            b1.setGuestEmail("arjun.sharma@example.com");
            b1.setGuestPhone("+91 98765 43210");
            b1.setTotalAmount(52498.0);
            b1.setDiscountApplied(0.0);
            b1.setStatus("Upcoming");
            b1.setPaymentStatus("Paid");
            b1.setPaymentMethod("UPI / Google Pay");
            b1.setCreatedAt("2026-11-02");
            b1 = bookingRepository.save(b1);
            paymentRepository.save(new Payment("TE-2026-8941", "UPI / Google Pay", "TXN-894101", 52498.0, "SUCCESS"));
            Ticket t1 = ticketService.generateTicketForBooking(b1);
            b1.setTicketId(t1.getTicketId());
            bookingRepository.save(b1);

            Booking b2 = new Booking();
            b2.setId("TE-2026-7512");
            b2.setPackageId("pkg-3");
            b2.setBookingType("PACKAGE");
            b2.setPackageTitle("Goa Beach Retreat & Sunset Catamaran Cruise");
            b2.setDestination("Goa, India");
            b2.setImage("https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=600&q=80");
            b2.setTravelDate("2026-10-14");
            b2.setReturnDate("2026-10-17");
            b2.setTravelers(2);
            b2.setGuestName("Priya Nair");
            b2.setGuestEmail("priya.nair@example.com");
            b2.setGuestPhone("+91 91234 56789");
            b2.setTotalAmount(28998.0);
            b2.setDiscountApplied(2000.0);
            b2.setStatus("Completed");
            b2.setPaymentStatus("Paid");
            b2.setPaymentMethod("Credit Card (HDFC)");
            b2.setCreatedAt("2026-09-20");
            b2 = bookingRepository.save(b2);
            paymentRepository.save(new Payment("TE-2026-7512", "Credit Card (HDFC)", "TXN-751202", 28998.0, "SUCCESS"));
            Ticket t2 = ticketService.generateTicketForBooking(b2);
            b2.setTicketId(t2.getTicketId());
            bookingRepository.save(b2);
        }
    }
}
