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
    private final PasswordEncoder passwordEncoder;

    public DataInitializationService(UserRepository userRepository,
                                     DestinationRepository destinationRepository,
                                     TravelPackageRepository packageRepository,
                                     HotelRepository hotelRepository,
                                     FlightRepository flightRepository,
                                     BookingRepository bookingRepository,
                                     PaymentRepository paymentRepository,
                                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
        this.packageRepository = packageRepository;
        this.hotelRepository = hotelRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedDestinations();
        seedPackages();
        seedHotels();
        seedFlights();
        seedBookings();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = new User(
                    "Super Admin",
                    "admin@tripease.com",
                    "+91 98765 00000",
                    passwordEncoder.encode("admin123"),
                    "ADMIN"
            );
            userRepository.save(admin);

            User user = new User(
                    "Arjun Sharma",
                    "arjun.sharma@example.com",
                    "+91 98765 43210",
                    passwordEncoder.encode("tripease123"),
                    "USER"
            );
            userRepository.save(user);
        }
    }

    private void seedDestinations() {
        if (destinationRepository.count() == 0) {
            destinationRepository.saveAll(List.of(
                    new Destination("kashmir", "Kashmir", "India", "North India", "Heaven on Earth", "Snow & Mountains", 24999.0, 14, "https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=800&q=80", "Pristine valleys, serene Shikara rides on Dal Lake, and snow-kissed Himalayan slopes in Gulmarg & Pahalgam.", "March to October", "14°C - 22°C"),
                    new Destination("kerala", "Kerala", "India", "South India", "God's Own Country", "Tropical & Backwaters", 19999.0, 18, "https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=800&q=80", "Tranquil backwater houseboats in Alleppey, emerald tea plantations in Munnar, and Ayurvedic wellness retreats.", "September to March", "23°C - 30°C"),
                    new Destination("goa", "Goa", "India", "West India", "Sun, Sand & Sea", "Beaches", 14499.0, 22, "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=800&q=80", "Golden palm-fringed beaches, Portuguese heritage churches, vibrant sunset shacks, and exhilarating water sports.", "October to April", "25°C - 32°C"),
                    new Destination("ladakh", "Ladakh", "India", "North India", "Land of High Passes", "Snow & Mountains", 32999.0, 9, "https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=800&q=80", "Mesmerizing blue waters of Pangong Tso, high mountain passes of Khardung La, and ancient Buddhist monasteries.", "May to September", "8°C - 18°C"),
                    new Destination("rajasthan", "Rajasthan", "India", "North India", "Royal Heritage", "Heritage & Palaces", 26499.0, 16, "https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=800&q=80", "Opulent palaces of Udaipur, towering golden forts of Jaisalmer, pink architecture of Jaipur, and desert camel safaris.", "October to March", "15°C - 28°C"),
                    new Destination("himachal", "Himachal Pradesh", "India", "North India", "Mountain Paradise", "Snow & Mountains", 16999.0, 20, "https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?auto=format&fit=crop&w=800&q=80", "Snowy adventures in Solang Valley, picturesque pines of Manali, colonial charm of Shimla, and cozy mountain cafes.", "All Year Round", "10°C - 20°C"),
                    new Destination("andaman", "Andaman Islands", "India", "Islands", "Exotic Coral Reefs", "Beaches", 31999.0, 11, "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?auto=format&fit=crop&w=800&q=80", "Radhanagar's pristine white sands, crystal-clear scuba diving waters, bioluminescence, and lush mangrove trails.", "October to May", "24°C - 30°C"),
                    new Destination("bali", "Bali", "Indonesia", "International", "Island of the Gods", "Tropical & Backwaters", 45999.0, 15, "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=800&q=80", "Iconic Tegallalang rice terraces, sacred sea temples in Tanah Lot, beach clubs in Seminyak, and private pool villas.", "April to October", "26°C - 31°C"),
                    new Destination("dubai", "Dubai", "United Arab Emirates", "International", "Futuristic Wonder", "Heritage & Palaces", 49999.0, 12, "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?auto=format&fit=crop&w=800&q=80", "Burj Khalifa sky deck, exhilarating 4x4 desert safaris with BBQ dinners, luxury marina cruises, and mega mall shopping.", "November to March", "20°C - 29°C"),
                    new Destination("maldives", "Maldives", "Maldives", "International", "Ultra Luxury Escape", "Beaches", 89999.0, 8, "https://images.unsplash.com/photo-1514282401047-d79a71a590e8?auto=format&fit=crop&w=800&q=80", "Iconic overwater bungalows over turquoise lagoons, romantic private beach dinners, manta ray snorkeling, and premier spa treatments.", "November to April", "27°C - 31°C"),
                    new Destination("switzerland", "Swiss Alps", "Switzerland", "International", "Alpine Romance", "Snow & Mountains", 129999.0, 6, "https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?auto=format&fit=crop&w=800&q=80", "Snow-capped peaks of Jungfraujoch, crystal lakes of Lucerne, scenic Glacier Express trains, and traditional chocolate tasting.", "May to October & Dec-Feb", "12°C - 22°C")
            ));
        }
    }

    private void seedPackages() {
        if (packageRepository.count() == 0) {
            TravelPackage pkg1 = new TravelPackage();
            pkg1.setId("pkg-1");
            pkg1.setTitle("Mesmerizing Kashmir & Dal Lake Paradise");
            pkg1.setDestination("Kashmir, India");
            pkg1.setRegion("North India");
            pkg1.setCategory("Honeymoon");
            pkg1.setDuration("5 Nights / 6 Days");
            pkg1.setDaysCount(6);
            pkg1.setNightsCount(5);
            pkg1.setPrice(24999.0);
            pkg1.setOriginalPrice(34999.0);
            pkg1.setRating(4.9);
            pkg1.setReviewsCount(148);
            pkg1.setBadge("Bestseller");
            pkg1.setFeatured(true);
            pkg1.setImage("https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80");
            pkg1.setOverview("Experience the timeless enchantment of Kashmir, celebrated as paradise on earth. Stay in a luxurious carved wooden houseboat on the serene Dal Lake, glide through floating flower markets on a private Shikara, ride the world-famous Gulmarg Gondola to snow peaks, and wander along the gushing Lidder river in Pahalgam.");
            pkg1.setHighlightsJson("[\"Traditional Deluxe Houseboat stay on Dal Lake with evening Shikara ride\",\"Gondola Cable Car Ride in Gulmarg up to Phase 1 snow point\",\"Picturesque drive through saffron fields and Apple Orchards in Pahalgam\",\"Visits to Mughal Gardens: Shalimar Bagh, Nishat Bagh & Chashme Shahi\",\"Authentic Kashmiri Wazwan dinner experience and Kahwa tea tasting\"]");
            pkg1.setInclusionsJson("[\"5 Nights Deluxe 4-Star Accommodation (4N Hotel + 1N Houseboat)\",\"Daily Morning Buffet Breakfast & Chef-curated Dinners\",\"Private AC Sedan for all transfers, sightseeing, and airport pick-and-drop\",\"1-Hour Complementary Sunset Shikara Ride on Dal Lake\",\"All toll taxes, parking fees, driver allowance & inner line permits\",\"24/7 Dedicated Trip Concierge & Local Guide Assistance\"]");
            pkg1.setExclusionsJson("[\"Airfare / Train tickets to and from Srinagar\",\"Gondola Phase 2 tickets (can be added on request)\",\"Pony rides, sledging, or snow scooter activities in Gulmarg/Pahalgam\",\"Personal expenses, laundry, telephone calls, and camera charges\",\"Travel Insurance (available as optional add-on during booking)\"]");
            pkg1.setStaysJson("[{\"name\":\"The Grand Mamta / Radisson Srinagar\",\"rating\":\"4.8★ Luxury\"},{\"name\":\"Royal Heritage Houseboat, Dal Lake\",\"rating\":\"4.9★ Heritage\"},{\"name\":\"Kolahoi Green Resort, Pahalgam\",\"rating\":\"4.7★ Scenic Resort\"}]");
            pkg1.setItineraryJson("[{\"day\":1,\"title\":\"Arrival in Srinagar & Sunset Shikara Ride\",\"description\":\"Arrive at Srinagar Airport, check into houseboat and enjoy sunset Shikara on Dal Lake.\"},{\"day\":2,\"title\":\"Gulmarg Meadow of Flowers & Gondola\",\"description\":\"Day trip to Gulmarg with gondola cable car ride.\"},{\"day\":3,\"title\":\"Pahalgam Valley of Shepherds\",\"description\":\"Drive through saffron fields to riverside resort in Pahalgam.\"},{\"day\":4,\"title\":\"Mughal Gardens Srinagar\",\"description\":\"Explore Shalimar and Nishat Mughal gardens.\"},{\"day\":5,\"title\":\"Sonmarg Meadow of Gold\",\"description\":\"Scenic excursion to Thajiwas Glacier.\"},{\"day\":6,\"title\":\"Airport Departure\",\"description\":\"Transfer to Srinagar Airport for onward journey.\"}]");
            pkg1.setGalleryJson("[\"https://images.unsplash.com/photo-1595815771614-ade9d652a65d?auto=format&fit=crop&w=1000&q=80\",\"https://images.unsplash.com/photo-1566837945700-30057527ade0?auto=format&fit=crop&w=1000&q=80\",\"https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg1);

            TravelPackage pkg2 = new TravelPackage();
            pkg2.setId("pkg-2");
            pkg2.setTitle("Enchanting Kerala Backwaters & Munnar Hills");
            pkg2.setDestination("Kerala, India");
            pkg2.setRegion("South India");
            pkg2.setCategory("Family");
            pkg2.setDuration("4 Nights / 5 Days");
            pkg2.setDaysCount(5);
            pkg2.setNightsCount(4);
            pkg2.setPrice(19999.0);
            pkg2.setOriginalPrice(27999.0);
            pkg2.setRating(4.8);
            pkg2.setReviewsCount(210);
            pkg2.setBadge("Trending");
            pkg2.setFeatured(true);
            pkg2.setImage("https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=1000&q=80");
            pkg2.setOverview("Unwind in the blissful embrace of God's Own Country. Traverse mist-cloaked tea estates in Munnar, witness endangered Nilgiri Tahr at Eravikulam, spice plantations in Thekkady, and spend a night on an authentic thatched houseboat cruising through the calm backwaters of Alleppey.");
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
            pkg3.setCategory("Beaches");
            pkg3.setDuration("3 Nights / 4 Days");
            pkg3.setDaysCount(4);
            pkg3.setNightsCount(3);
            pkg3.setPrice(14499.0);
            pkg3.setOriginalPrice(21999.0);
            pkg3.setRating(4.7);
            pkg3.setReviewsCount(320);
            pkg3.setBadge("Popular Deal");
            pkg3.setFeatured(true);
            pkg3.setImage("https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80");
            pkg3.setOverview("Sun, golden sands, lively shacks, and rich Portuguese colonial history. Relax by your resort pool, discover historic churches in Old Goa, and set sail on an energetic sunset cruise on the Mandovi River.");
            pkg3.setHighlightsJson("[\"Stay at a 4-Star Beachside Resort with swimming pool and buffet breakfast\",\"Mandovi River Sunset Cruise with Goan folk music and DJ\",\"North Goa Beach Tour: Baga, Calangute, Anjuna & Chapora Fort\"]");
            pkg3.setInclusionsJson("[\"3 Nights accommodation at premium beach resort\",\"Daily Breakfast Buffet\",\"Goa Airport / Railway station pickup & drop\",\"Complimentary Mandovi River cruise tickets\"]");
            pkg3.setExclusionsJson("[\"Flights to/from Goa\",\"Water sports fees\",\"Personal bar and nightclub expenses\"]");
            pkg3.setStaysJson("[{\"name\":\"Acron Waterfront Resort / Fairfield Marriott\",\"rating\":\"4.7★ Beachside\"}]");
            pkg3.setItineraryJson("[{\"day\":1,\"title\":\"Arrival Goa\",\"description\":\"Airport transfer to beach resort.\"},{\"day\":2,\"title\":\"North Goa Beaches\",\"description\":\"Baga, Calangute and Chapora Fort.\"},{\"day\":3,\"title\":\"Old Goa & Sunset Cruise\",\"description\":\"Basilica of Bom Jesus and Mandovi river cruise.\"},{\"day\":4,\"title\":\"Departure\",\"description\":\"Cab transfer to airport.\"}]");
            pkg3.setGalleryJson("[\"https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg3);

            TravelPackage pkg4 = new TravelPackage();
            pkg4.setId("pkg-4");
            pkg4.setTitle("Mystical Ladakh & Pangong Tso Expedition");
            pkg4.setDestination("Ladakh, India");
            pkg4.setRegion("North India");
            pkg4.setCategory("Adventure");
            pkg4.setDuration("6 Nights / 7 Days");
            pkg4.setDaysCount(7);
            pkg4.setNightsCount(6);
            pkg4.setPrice(32999.0);
            pkg4.setOriginalPrice(44999.0);
            pkg4.setRating(4.9);
            pkg4.setReviewsCount(94);
            pkg4.setBadge("Adventure Top Pick");
            pkg4.setFeatured(true);
            pkg4.setImage("https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=1000&q=80");
            pkg4.setOverview("Embark on an epic Himalayan expedition through the high-altitude desert of Ladakh. Cross the dizzying Khardung La pass (17,582 ft) and camp by Pangong Lake.");
            pkg4.setHighlightsJson("[\"Lakeside luxury Swiss cottage tent stay at Pangong Tso\",\"Double-humped camel ride in Nubra Valley Hunder dunes\",\"Conquer Khardung La pass\"]");
            pkg4.setInclusionsJson("[\"6 Nights Accommodations (4N Leh, 1N Nubra, 1N Pangong Camp)\",\"Daily Breakfast & Dinners\",\"Dedicated SUV (Innova / Scorpio)\",\"Inner line permits & Oxygen cylinder\"]");
            pkg4.setExclusionsJson("[\"Flights to Leh\",\"Personal camel ride or rafting fees\"]");
            pkg4.setStaysJson("[{\"name\":\"The Grand Dragon Ladakh, Leh\",\"rating\":\"4.8★ Luxury\"},{\"name\":\"Pangong Star Glamping Tents\",\"rating\":\"4.9★ Lakeside\"}]");
            pkg4.setItineraryJson("[{\"day\":1,\"title\":\"Leh Acclimatization\",\"description\":\"Arrive and rest.\"},{\"day\":2,\"title\":\"Magnetic Hill & Sangam\",\"description\":\"Sightseeing.\"},{\"day\":3,\"title\":\"Nubra Valley via Khardung La\",\"description\":\"Drive over high pass.\"},{\"day\":4,\"title\":\"Pangong Lake\",\"description\":\"Camp under stars.\"},{\"day\":5,\"title\":\"Back to Leh\",\"description\":\"Via Chang La pass.\"},{\"day\":6,\"title\":\"Shey & Thiksey Monasteries\",\"description\":\"Culture tour.\"},{\"day\":7,\"title\":\"Departure\",\"description\":\"Airport transfer.\"}]");
            pkg4.setGalleryJson("[\"https://images.unsplash.com/photo-1581793745862-99fde7fa73d2?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg4);

            TravelPackage pkg5 = new TravelPackage();
            pkg5.setId("pkg-5");
            pkg5.setTitle("Royal Rajasthan: Jaipur, Jodhpur & Udaipur");
            pkg5.setDestination("Rajasthan, India");
            pkg5.setRegion("North India");
            pkg5.setCategory("Heritage");
            pkg5.setDuration("5 Nights / 6 Days");
            pkg5.setDaysCount(6);
            pkg5.setNightsCount(5);
            pkg5.setPrice(26499.0);
            pkg5.setOriginalPrice(36999.0);
            pkg5.setRating(4.8);
            pkg5.setReviewsCount(112);
            pkg5.setBadge("Royal Special");
            pkg5.setFeatured(false);
            pkg5.setImage("https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=1000&q=80");
            pkg5.setOverview("Step back into the era of valiant Maharajas and regal palaces in Jaipur, Jodhpur, and Udaipur.");
            pkg5.setHighlightsJson("[\"Stay at authentic heritage Havelis\",\"Lake Pichola boat cruise in Udaipur\",\"Amber Fort sound and light show\"]");
            pkg5.setInclusionsJson("[\"5 Nights Deluxe Heritage Hotel Stays\",\"Daily Breakfast & Royal Dinners\",\"Private AC Sedan\",\"Boat ride tickets on Lake Pichola\"]");
            pkg5.setExclusionsJson("[\"Flights/train tickets\",\"Monument entrance fees\"]");
            pkg5.setStaysJson("[{\"name\":\"Alsisar Haveli, Jaipur\",\"rating\":\"4.8★ Heritage\"},{\"name\":\"Fateh Garh Palace, Udaipur\",\"rating\":\"4.9★ Lake View\"}]");
            pkg5.setItineraryJson("[{\"day\":1,\"title\":\"Jaipur Arrival\",\"description\":\"City Palace & local bazaars.\"},{\"day\":2,\"title\":\"Amber Fort & Hawa Mahal\",\"description\":\"Fort tour.\"},{\"day\":3,\"title\":\"Jaipur to Jodhpur\",\"description\":\"Mehrangarh Fort.\"},{\"day\":4,\"title\":\"To Udaipur\",\"description\":\"Via Ranakpur temples.\"},{\"day\":5,\"title\":\"Udaipur Sightseeing\",\"description\":\"Lake Pichola & City Palace.\"},{\"day\":6,\"title\":\"Departure\",\"description\":\"Airport transfer.\"}]");
            pkg5.setGalleryJson("[\"https://images.unsplash.com/photo-1599661046289-e31897846e41?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg5);

            TravelPackage pkg6 = new TravelPackage();
            pkg6.setId("pkg-6");
            pkg6.setTitle("Bali Tropical Bliss & Ubud Cultural Sanctuary");
            pkg6.setDestination("Bali, Indonesia");
            pkg6.setRegion("International");
            pkg6.setCategory("International");
            pkg6.setDuration("6 Nights / 7 Days");
            pkg6.setDaysCount(7);
            pkg6.setNightsCount(6);
            pkg6.setPrice(45999.0);
            pkg6.setOriginalPrice(62000.0);
            pkg6.setRating(4.9);
            pkg6.setReviewsCount(160);
            pkg6.setBadge("International Favorite");
            pkg6.setFeatured(true);
            pkg6.setImage("https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=1000&q=80");
            pkg6.setOverview("Surround yourself with Bali's emerald rice terraces, sacred waterfalls, ancient sea temples, and vibrant coastal culture.");
            pkg6.setHighlightsJson("[\"Private Pool Villa stay in Seminyak\",\"Ubud Bali Swing and Tegallalang rice terraces\",\"Nusa Penida island speedboat tour\"]");
            pkg6.setInclusionsJson("[\"6 Nights stay (3N Ubud + 3N Seminyak Villa)\",\"Daily Breakfast + 1 Special Floating Breakfast\",\"Private AC car with English-speaking chauffeur\"]");
            pkg6.setExclusionsJson("[\"International flights to Bali\",\"Indonesian Visa on arrival\"]");
            pkg6.setStaysJson("[{\"name\":\"Kamandalu Ubud Resort\",\"rating\":\"4.9★ Rainforest\"},{\"name\":\"The Seminyak Beach Resort\",\"rating\":\"4.8★ Beachfront\"}]");
            pkg6.setItineraryJson("[{\"day\":1,\"title\":\"Bali Arrival\",\"description\":\"Transfer to Ubud resort.\"},{\"day\":2,\"title\":\"Ubud Rice Fields & Swing\",\"description\":\"Tegallalang and waterfalls.\"},{\"day\":3,\"title\":\"Mount Batur Volcano\",\"description\":\"Coffee plantation & volcano view.\"},{\"day\":4,\"title\":\"Nusa Penida Tour\",\"description\":\"Kelingking T-Rex beach tour.\"},{\"day\":5,\"title\":\"Tanah Lot Temple\",\"description\":\"Sunset temple visit.\"},{\"day\":6,\"title\":\"Uluwatu Kecak Dance\",\"description\":\"Cliffside temple.\"},{\"day\":7,\"title\":\"Departure\",\"description\":\"Airport transfer.\"}]");
            pkg6.setGalleryJson("[\"https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=1000&q=80\"]");
            packageRepository.save(pkg6);
        }
    }

    private void seedHotels() {
        if (hotelRepository.count() == 0) {
            hotelRepository.saveAll(List.of(
                    new Hotel("The Grand Mamta Srinagar", "Kashmir", "Srinagar", 4.8, 6500.0, "https://images.unsplash.com/photo-1566837945700-30057527ade0?auto=format&fit=crop&w=800&q=80", "Deluxe Room", "Free WiFi, Mountain View Balcony, Heating, Restaurant", true),
                    new Hotel("Amber Dale Luxury Resort", "Kerala", "Munnar", 4.8, 5500.0, "https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?auto=format&fit=crop&w=800&q=80", "Valley View Suite", "Tea Garden View, Spa, Free Breakfast, Multi-Cuisine Diner", true),
                    new Hotel("Acron Waterfront Resort", "Goa", "Baga", 4.7, 4800.0, "https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?auto=format&fit=crop&w=800&q=80", "Poolside Villa Room", "Swimming Pool, Beach Access, Riverfront Bar, Free WiFi", true),
                    new Hotel("The Grand Dragon Ladakh", "Ladakh", "Leh", 4.9, 9200.0, "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=800&q=80", "Premier Mountain View", "Oxygen Supply, Central Heating, Cultural Dining, Spa", true),
                    new Hotel("Alsisar Haveli", "Rajasthan", "Jaipur", 4.8, 7000.0, "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?auto=format&fit=crop&w=800&q=80", "Heritage Royal Room", "Swimming Pool, Courtyard Diners, Rajasthani Folk Show", true),
                    new Hotel("Kamandalu Ubud Resort", "Bali", "Ubud", 4.9, 14500.0, "https://images.unsplash.com/photo-1537996194471-e657df975ab4?auto=format&fit=crop&w=800&q=80", "Private Pool Villa", "Infinity Pool, Floating Breakfast, Forest Spa, Jungle View", true)
            ));
        }
    }

    private void seedFlights() {
        if (flightRepository.count() == 0) {
            flightRepository.saveAll(List.of(
                    new Flight("6E-2041", "IndiGo", "Delhi", "Srinagar", "06:15 AM", "07:45 AM", 4800.0, "Economy", 45),
                    new Flight("AI-852", "Air India", "Mumbai", "Srinagar", "08:30 AM", "11:15 AM", 6200.0, "Economy", 32),
                    new Flight("6E-512", "IndiGo", "Bengaluru", "Cochin", "07:00 AM", "08:10 AM", 3200.0, "Economy", 50),
                    new Flight("UK-745", "Vistara", "Delhi", "Goa", "09:40 AM", "12:15 PM", 5400.0, "Premium Economy", 24),
                    new Flight("6E-112", "IndiGo", "Delhi", "Leh", "05:45 AM", "07:10 AM", 7100.0, "Economy", 18),
                    new Flight("AI-991", "Air India", "Mumbai", "Dubai", "10:00 AM", "11:45 AM", 14500.0, "Economy", 40)
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
            b1.setStatus("Upcoming");
            b1.setPaymentStatus("Paid");
            b1.setPaymentMethod("UPI / Google Pay");
            b1.setCreatedAt("2026-11-02");
            bookingRepository.save(b1);
            paymentRepository.save(new Payment("TE-2026-8941", "UPI / Google Pay", "TXN-894101", 52498.0, "SUCCESS"));

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
            b2.setStatus("Completed");
            b2.setPaymentStatus("Paid");
            b2.setPaymentMethod("Credit Card (HDFC)");
            b2.setCreatedAt("2026-09-20");
            bookingRepository.save(b2);
            paymentRepository.save(new Payment("TE-2026-7512", "Credit Card (HDFC)", "TXN-751202", 28998.0, "SUCCESS"));

            Booking b3 = new Booking();
            b3.setId("TE-2026-6204");
            b3.setPackageId("pkg-2");
            b3.setBookingType("PACKAGE");
            b3.setPackageTitle("Enchanting Kerala Backwaters & Munnar Hills");
            b3.setDestination("Kerala, India");
            b3.setImage("https://images.unsplash.com/photo-1602216056096-3b40cc0c9944?auto=format&fit=crop&w=600&q=80");
            b3.setTravelDate("2026-08-10");
            b3.setReturnDate("2026-08-15");
            b3.setTravelers(3);
            b3.setGuestName("Rahul Verma");
            b3.setGuestEmail("rahul.v@example.com");
            b3.setGuestPhone("+91 99887 76655");
            b3.setTotalAmount(59997.0);
            b3.setStatus("Completed");
            b3.setPaymentStatus("Paid");
            b3.setPaymentMethod("Net Banking");
            b3.setCreatedAt("2026-07-15");
            bookingRepository.save(b3);
            paymentRepository.save(new Payment("TE-2026-6204", "Net Banking", "TXN-620403", 59997.0, "SUCCESS"));
        }
    }
}

