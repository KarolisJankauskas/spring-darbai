import { NavLink, Outlet } from "react-router-dom";
import { useAuth } from "../context/AuthContext.jsx";

export const MainLayout = () => {
    const { logout } = useAuth();

    const handleLogout = async () => {
        try {
            await logout();
        } catch (error) {
            console.error("Logout failed:", error);
        }
    };

    return (
        <div className="grid grid-rows-[5rem_1fr_5rem] h-screen">
            {/* Navbar */}
            <nav className="navbar bg-primary text-primary-content">
                <NavLink to="/movies" className="btn btn-ghost text-xl" key="reedery">
                    Reedery
                </NavLink>
                <div className="flex-none hidden lg:flex">
                    <div className="flex items-stretch">
                        <button
                            onClick={handleLogout}
                            className="btn btn-ghost"
                            aria-label="Logout"
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </nav>

            <main>
                <Outlet />
            </main>

            <footer className="bg-gray-800 text-white py-8 mt-auto border-t border-gray-700">
            <div className="container mx-auto px-4 text-center">
                <p className="text-gray-400">
                    &copy; {new Date().getFullYear()} MovieApp. All rights reserved.
                </p>
                <p className="text-gray-400 text-sm mt-2">
                    Made with ❤️ by Your Company Name
                </p>
            </div>
        </footer>
        </div>
    );
};