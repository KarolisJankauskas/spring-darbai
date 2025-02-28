export const Footer = () => {
    return (
        <footer className="bg-gray-800 text-white py-8 mt-auto border-t border-gray-700">
            <div className="container mx-auto px-4 text-center">
                <p className="text-gray-400">
                    &copy; {new Date().getFullYear()} AddApp. All rights reserved.
                </p>
                <p className="text-gray-400 text-sm mt-2">
                    Made with ❤️ by Your Company Name
                </p>
            </div>
        </footer>
    );
};