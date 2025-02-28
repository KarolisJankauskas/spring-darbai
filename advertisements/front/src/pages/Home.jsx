import { NavLink } from "react-router";

export const Home = () => {
    return (
        <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-4">
            <h1 className="text-4xl font-bold text-gray-800 mb-4">
                Welcome to AdsApp
            </h1>
            <p className="text-gray-600 text-lg text-center max-w-2xl mb-8">
                Here you can see many diferent ads and ofcourse add yours!!!!!
            </p>
            <NavLink
                to="/movies"
                className="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700 transition-colors duration-300"
            >
                Explore Ads
            </NavLink>
        </div>
    );
};