import { NavLink } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export const Navbar = () => {
    const { user, logout } = useAuth(); // Assuming useAuth provides user and logout

    return (
        <nav className="bg-white shadow-md fixed w-full top-0 z-50">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16">
                    {/* Logo and Links */}
                    <div className="flex items-center">
                        <NavLink
                            to="/"
                            className="text-2xl font-bold text-gray-800 hover:text-blue-600 transition-colors duration-300"
                        >
                            AdApp
                        </NavLink>
                        <div className="hidden md:flex space-x-4 ml-10">
                            <NavLink
                                to="/ads"
                                className="text-gray-600 hover:text-blue-600 px-3 py-2 rounded-md text-sm font-medium transition-colors duration-300"
                                activeClassName="text-blue-600"
                            >
                                Ads
                            </NavLink>
                            <NavLink
                                to="/about"
                                className="text-gray-600 hover:text-blue-600 px-3 py-2 rounded-md text-sm font-medium transition-colors duration-300"
                                activeClassName="text-blue-600"
                            >
                                About
                            </NavLink>
                            <NavLink
                                to="/contact"
                                className="text-gray-600 hover:text-blue-600 px-3 py-2 rounded-md text-sm font-medium transition-colors duration-300"
                                activeClassName="text-blue-600"
                            >
                                Contact
                            </NavLink>
                        </div>
                    </div>

                    {/* User Section */}
                    <div className="flex items-center">
                        {user ? (
                            <div className="flex items-center space-x-4">
                                <span className="text-gray-800 text-sm font-medium">
                                    Welcome, {user.username}
                                </span>
                                <button
                                    onClick={logout}
                                    className="bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700 transition-colors duration-300"
                                >
                                    Logout
                                </button>
                            </div>
                        ) : (
                            <div className="flex items-center space-x-4">
                                <NavLink
                                    to="/login"
                                    className="text-gray-600 hover:text-blue-600 px-3 py-2 rounded-md text-sm font-medium transition-colors duration-300"
                                >
                                    Login
                                </NavLink>
                                <NavLink
                                    to="/register"
                                    className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition-colors duration-300"
                                >
                                    Register
                                </NavLink>
                            </div>
                        )}
                    </div>
                </div>
            </div>

            {/* Mobile Menu (Hamburger) */}
            <div className="md:hidden">
                <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3">
                    <NavLink
                        to="/movies"
                        className="text-gray-600 hover:text-blue-600 block px-3 py-2 rounded-md text-base font-medium transition-colors duration-300"
                        activeClassName="text-blue-600"
                    >
                        Ads
                    </NavLink>
                    <NavLink
                        to="/about"
                        className="text-gray-600 hover:text-blue-600 block px-3 py-2 rounded-md text-base font-medium transition-colors duration-300"
                        activeClassName="text-blue-600"
                    >
                        About
                    </NavLink>
                    <NavLink
                        to="/contact"
                        className="text-gray-600 hover:text-blue-600 block px-3 py-2 rounded-md text-base font-medium transition-colors duration-300"
                        activeClassName="text-blue-600"
                    >
                        Contact
                    </NavLink>
                    {!user && (
                        <>
                            <NavLink
                                to="/login"
                                className="text-gray-600 hover:text-blue-600 block px-3 py-2 rounded-md text-base font-medium transition-colors duration-300"
                            >
                                Login
                            </NavLink>
                            <NavLink
                                to="/register"
                                className="text-gray-600 hover:text-blue-600 block px-3 py-2 rounded-md text-base font-medium transition-colors duration-300"
                            >
                                Register
                            </NavLink>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
};